package com.skipthediches.challenge.service;

import com.skipthediches.challenge.service.entity.EmailMessage;
import com.skipthediches.challenge.service.service.EmailSenderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailCompositionServiceTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    public void sendEmailUsingSMTPServer() {
        EmailMessage emailMessage = new EmailMessage(
                "aurindo@gmail.com",
                "Subject Test",
                "Only a test. It is a body!"
        );

        String emailReturn = emailSenderService.sendEmail(emailMessage);

        Assert.assertEquals("Sent to " + emailMessage.getTo(), emailReturn);
    }

}
