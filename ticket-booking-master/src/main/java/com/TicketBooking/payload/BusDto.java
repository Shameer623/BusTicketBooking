package com.TicketBooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    private long id;
    private String busNo;
    private int totalSeats;
    private int availableSeats;
    private String busType;
    private long price;
}
