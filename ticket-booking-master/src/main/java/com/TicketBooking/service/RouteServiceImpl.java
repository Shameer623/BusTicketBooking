package com.TicketBooking.service;

import com.TicketBooking.Repository.BusRepository;
import com.TicketBooking.Repository.RouteRepository;
import com.TicketBooking.Repository.SubRouteRepository;
import com.TicketBooking.entity.Bus;
import com.TicketBooking.entity.Route;
import com.TicketBooking.entity.SubRoute;
import com.TicketBooking.exception.ResourseNotFoundException;
import com.TicketBooking.payload.RouteDto;
import com.TicketBooking.payload.searchBusDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



@Service
public class RouteServiceImpl {
    private RouteRepository routeRepository;
    private ModelMapper mapper;
    private BusRepository busRepository ;
    @Autowired
    private SubRouteRepository subRouteRepository;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper mapper, BusRepository busRepository) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
        this.busRepository = busRepository;
    }

    public ResponseEntity<?> addRoute(long busId, RouteDto routeDto) {
        if(!routeRepository.existsByBusId(busId)){
            if(busRepository.existsById(busId)){
                Route route = mapper.map(routeDto,Route.class);
                route.setBusId(busId);
                Route saved = routeRepository.save(route);
                return new ResponseEntity<>(saved, HttpStatus.CREATED);
            }
        }
        else {
            return new ResponseEntity<>(
                    new ResourseNotFoundException("Bus already Assigned to a route"),
                    HttpStatus.BAD_REQUEST);
        }

        return null;
    }
// Search Bus By Location and Date
    public ResponseEntity<?> findBus(String fromLocation, String toLocation, String fromDate) {

        LocalDate bookingDate = LocalDate.parse(fromDate);

        LocalDate currentDate = LocalDate.now();


        if (currentDate.isBefore(bookingDate)) {

            List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(
                    fromLocation, toLocation, fromDate);
            List<SubRoute> subRoutes = subRouteRepository.findByFromLocationAndToLocationAndFromDate(
                    fromLocation, toLocation, fromDate);
            if (routes != null) {
                for (Route route : routes) {
                    Bus bus = busRepository.findById(route.getBusId()).get();
                    searchBusDto dto = mapToSearchDto(bus, route);
                    return new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }
            if (subRoutes != null) {
                for (SubRoute route : subRoutes) {
                    Route RouteId = routeRepository.findById(route.getRouteId()).get();
                    Bus bus = busRepository.findById(RouteId.getBusId()).get();
                    searchBusDto dto = mapToSearchDto(bus, route);
                    return new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Please Enter Correct Date",HttpStatus.BAD_REQUEST);


    }
    searchBusDto mapToSearchDto(Bus bus, Route route){
        searchBusDto dto = new searchBusDto();
        dto.setBusId(bus.getId());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAvailableSeats(bus.getAvailableSeats());
        dto.setBusType(bus.getBusType());
        dto.setBusNo(bus.getBusNo());
        dto.setPrice(bus.getPrice());
        dto.setRouteId(route.getId());
        dto.setFromDestination(route.getFromLocation());
        dto.setToDestination(route.getToLocation());
        dto.setToDate(route.getToDate());
        dto.setFromDate(route.getFromDate());
        dto.setTotalDistance(route.getTotalDistance());
        dto.setArrivalTime(route.getArrivalTime());
        dto.setDepartureTime(route.getDepartureTime());
        return dto;
    }
    searchBusDto mapToSearchDto(Bus bus, SubRoute route){
        searchBusDto dto = new searchBusDto();
        dto.setBusId(bus.getId());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAvailableSeats(bus.getAvailableSeats());
        dto.setBusType(bus.getBusType());
        dto.setBusNo(bus.getBusNo());
        dto.setPrice(bus.getPrice());
        dto.setRouteId(route.getId());
        dto.setFromDestination(route.getFromLocation());
        dto.setToDestination(route.getToLocation());
        dto.setToDate(route.getToDate());
        dto.setFromDate(route.getFromDate());
        dto.setTotalDistance(route.getTotalDistance());
        dto.setArrivalTime(route.getArrivalTime());
        dto.setDepartureTime(route.getDepartureTime());
        return dto;
    }

    public ResponseEntity<?> deleteRoute(long routeId) {
        if (routeRepository.existsById(routeId)) {
            if (subRouteRepository.existsByRouteId(routeId)) {
                subRouteRepository.deleteByRouteId(routeId);
            }
            routeRepository.deleteById(routeId);
            return  new ResponseEntity<>("Route is Deleted by Id : "+routeId,HttpStatus.OK);
        }
        return new ResponseEntity<>("route is not available with Id : "+routeId,HttpStatus.BAD_REQUEST);
    }
}
