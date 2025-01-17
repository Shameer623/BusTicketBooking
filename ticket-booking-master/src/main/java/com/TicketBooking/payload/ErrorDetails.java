package com.TicketBooking.payload;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetails {
    private String message;
    private String uri;
    private Date date;
}
