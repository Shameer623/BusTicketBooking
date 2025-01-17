package com.TicketBooking.util;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.TicketBooking.Repository.BusRepository;
import com.TicketBooking.entity.Passenger;
import com.TicketBooking.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {
    @Autowired
    private BusRepository busRepository;

    public byte[] generatePDFTicket(Passenger passenger, Route route) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            document.add(new Paragraph("Ticket Details:"));
            document.add(new Paragraph("Passenger: " + passenger.getFirstName() + " " + passenger.getLastName()));
            document.add(new Paragraph("Age: " + passenger.getAge()));
            document.add(new Paragraph("Gender: " + passenger.getGender()));
            document.add(new Paragraph("Contact Number: " + passenger.getContactNumber()));
            document.add(new Paragraph("Departure Time: " + route.getDepartureTime()));
            document.add(new Paragraph("Arrival Time: " + route.getArrivalTime()));
            document.add(new Paragraph("From: " + route.getFromLocation()));
            document.add(new Paragraph("To: " + route.getToLocation()));
            document.add(new Paragraph("Date: " + route.getFromDate()));
            document.add(new Paragraph("Bus ID: " + route.getBusId()));
            document.add(new Paragraph("Bus Number: " + busRepository.findById(route.getBusId()).get().getBusNo()));
            document.add(new Paragraph("Route ID: " + route.getId()));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}

