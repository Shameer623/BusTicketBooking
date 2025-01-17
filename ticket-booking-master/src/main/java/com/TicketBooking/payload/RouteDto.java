package com.TicketBooking.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {

    private String fromDate;
    private String toDate;
    private String fromLocation;
    private String toLocation;
    private String arrivalTime;
    private String departureTime;
    private long totalDistance;

}
