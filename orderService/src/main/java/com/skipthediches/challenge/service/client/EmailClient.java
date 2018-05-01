package com.skipthediches.challenge.service.client;

import com.skipthediches.challenge.service.entity.EmailMessageOther;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient("email-service")
public interface EmailClient {

    @RequestMapping(method = RequestMethod.POST, value = "/email")
    ResponseEntity<String> send(
            @Valid @RequestBody EmailMessageOther emailMessage
    );

}
