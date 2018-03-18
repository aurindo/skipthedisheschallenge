package com.skipthediches.challenge.service;

import com.skipthediches.challenge.service.service.CabBookingService;
import com.skipthediches.challenge.service.service.CabBookingServiceImpl;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RAbbitMQConfiguration {

    @Bean
    Queue queue() {
        return new Queue("remotingQueue");
    }

    @Bean
    CabBookingService bookingService() {
        return new CabBookingServiceImpl();
    }

    @Bean
    AmqpInvokerServiceExporter exporter(
            CabBookingService implementation, AmqpTemplate template) {

        AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
        exporter.setServiceInterface(CabBookingService.class);
        exporter.setService(implementation);
        exporter.setAmqpTemplate(template);
        return exporter;
    }

    @Bean
    SimpleMessageListenerContainer listener(
            ConnectionFactory facotry,
            AmqpInvokerServiceExporter exporter,
            Queue queue) {

        SimpleMessageListenerContainer container
                = new SimpleMessageListenerContainer(facotry);
        container.setMessageListener(exporter);
        container.setQueueNames(queue.getName());
        return container;
    }

}
