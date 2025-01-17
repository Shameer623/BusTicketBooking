package com.TicketBooking.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String emailOrUsername;
    private String password;
}
