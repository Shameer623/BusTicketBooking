package com.TicketBooking.service;

import com.TicketBooking.Repository.BusRepository;
import com.TicketBooking.Repository.PassengerRepository;
import com.TicketBooking.Repository.RouteRepository;
import com.TicketBooking.Repository.SubRouteRepository;
import com.TicketBooking.entity.Passenger;
import com.TicketBooking.entity.Route;
import com.TicketBooking.util.EmailSendService;
import com.TicketBooking.util.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;


@Service
public class ReservationServiceImpl {
    private BusRepository busRepository;
    private RouteRepository routeRepository;
    private SubRouteRepository subRouteRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private EmailSendService emailService;
    @Autowired
    private PdfService pdfService;

    @Autowired
    private BusServiceImpl busService;

    public ReservationServiceImpl(BusRepository busRepository, RouteRepository routeRepository, SubRouteRepository subRouteRepository) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.subRouteRepository = subRouteRepository;
    }
@Transactional
    public String bookTicket(long busId, long routeId, Passenger passenger) throws IOException, MessagingException {
        boolean busIsPresent = busRepository.existsById(busId);
        boolean routeIsPresent = routeRepository.existsById(routeId);
        //Bug
        boolean subRouteIsPresent = subRouteRepository.existsByRouteId(routeId);

        if(busIsPresent&&routeIsPresent || busIsPresent&&subRouteIsPresent){
            Route route = routeRepository.findById(routeId).get();
            Passenger saved = passengerRepository.save(passenger);

            if(busService.updateAvailableSeat(busId)>0) {


                byte[] ticket = pdfService.generatePDFTicket(saved, route);

                emailService.sendEmailWithAttachment(saved.getEmail(), "Booking Ticket for journey", "Your ticket for journey" +
                                route.getFromLocation() + " to " + route.getToLocation() + " Date " + route.getFromDate(), ticket,
                        passenger.getFirstName() + "Ticket");

            return "Booking Done";
            }
        }
        else {
            return "Booking Can not be done";
        }

    return null;
    }
}
