package com.TicketBooking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailSendService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendEmailWithAttachment(String recipientEmail, String subject, String body, byte[] attachment, String attachmentName) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();

        // Enable multipart mode
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(senderEmail);
        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(body);

        // Add attachment
        ByteArrayResource resource = new ByteArrayResource(attachment);
        helper.addAttachment(attachmentName, resource,"application/pdf");

        // Send email
        javaMailSender.send(message);
    }

    public void SendOtpViaEmail(String To,String subject,String body){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(To);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(senderEmail);

        javaMailSender.send(message);
    }
}

