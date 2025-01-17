package com.TicketBooking.controller;

import com.TicketBooking.entity.Role;
import com.TicketBooking.enums.Roles;
import com.TicketBooking.payload.UserDto;
import com.TicketBooking.payload.UserRegistrationDto;
import com.TicketBooking.service.UserRegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegController {
    @Autowired
    private UserRegistrationServiceImpl userService;
    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestParam("name") String name,
            @RequestParam("userName") String userName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("role") List<String> role

    ){
        UserDto dto = new UserDto();
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setName(name);
        dto.setRoles(role);
        dto.setUsername(userName);
        return userService.saveUser(dto, profilePicture);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findUser(@PathVariable long id){
        UserRegistrationDto user = userService.getUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email,@RequestParam String otp){
       return userService.verifyOtp(email,otp);
    }

}
