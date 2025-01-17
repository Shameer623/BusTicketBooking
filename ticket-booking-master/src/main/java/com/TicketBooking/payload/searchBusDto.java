package com.TicketBooking.payload;

import lombok.Data;

@Data
public class searchBusDto {
    private long BusId;
    private String busNo;
    private int totalSeats;
    private int availableSeats;
    private String busType;
    private long price;
    private long routeId;
    private String fromDate;
    private String toDate;
    private String fromDestination;
    private String toDestination;
    private String arrivalTime;
    private String departureTime;
    private long totalDistance;
}
