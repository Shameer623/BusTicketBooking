package com.TicketBooking.payload;

import com.TicketBooking.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private List<String> roles;

}
