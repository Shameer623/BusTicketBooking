package com.TicketBooking.service;

import com.TicketBooking.util.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailServiceImpl {
    @Autowired
    private EmailSendService emailSendService;
    static public Map<String,String> emailAndOtp =new HashMap<>();

    public String generateOtp() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    public void sendOtp(String email) {
        String otp = generateOtp();
        emailAndOtp.put(email,otp);
        emailSendService.SendOtpViaEmail(email,"Otp for verification","Your Otp is : "+otp);
    }
}
