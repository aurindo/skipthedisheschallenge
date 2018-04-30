package com.skipthediches.challenge.service.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.skipthediches.challenge.service.entity.EmailMessage;
import com.skipthediches.challenge.service.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/email")
public class SenderEmailResource {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping
    @HystrixCommand(fallbackMethod = "sendCircuitBreaker")
    public ResponseEntity<String> send(
            @Valid @RequestBody EmailMessage emailMessage
        ) {

        String methodReturn = emailSenderService.sendEmail(emailMessage);

        return ResponseEntity.ok(methodReturn);
    }

    public ResponseEntity<String> sendCircuitBreaker(
            @Valid @RequestBody EmailMessage emailMessage
        ) {

        String methodReturn = "";

        int attempt = 1;
        try {
            while (attempt < 5) {
                Thread.sleep(1000);
                try {
                    emailMessage.setSubject(emailMessage.getSubject() + attempt);
                    methodReturn = emailSenderService.sendEmail(emailMessage);
                } catch (Exception e) {
                    System.out.println("Attempt " + attempt++);
                }

                return ResponseEntity.ok(methodReturn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Please try again after some minutes");

    }

}
