package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    public JavaMailSender emailSender;

    public String sendEmail(EmailMessage emailMessage) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getTo());
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getBody());

        emailSender.send(message);

        return "Sent to " + emailMessage.getTo();
    }
}
