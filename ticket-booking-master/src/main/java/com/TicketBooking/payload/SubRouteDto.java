package com.TicketBooking.payload;

import lombok.Data;

@Data
public class SubRouteDto {

    private String subRouteName;
    private String fromDate;
    private String toDate;
    private String fromLocation;
    private String toLocation;
    private String arrivalTime;
    private String departureTime;
    private long totalDistance;

}
