package com.skipthediches.challenge.service;

import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.service.OrderCustomerService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Date;

@Configuration
@RabbitListener(queues = "remotingQueue")
public class RAbbitMQConfiguration {

    @Autowired
    private OrderCustomerService orderCustomerService;

    @Bean
    public Queue getQueue() {
        return new Queue("remotingQueue");
    }

    @RabbitHandler
    public void process(@Payload OrderCustomer orderCustomer) throws Exception {
        System.out.println("====> " + new Date() + ": " + orderCustomer);
        orderCustomerService.save(orderCustomer);
    }

}
