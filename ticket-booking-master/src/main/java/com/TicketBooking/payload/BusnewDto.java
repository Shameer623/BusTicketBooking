package com.TicketBooking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusnewDto {
    private long id;
    private int totalSeats;
    private int availableSeats;
    private long price;
}
