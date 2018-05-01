package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.client.EmailClient;
import com.skipthediches.challenge.service.entity.EmailMessageOther;
import com.skipthediches.challenge.service.entity.OrderCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProcessCustomerOrderImpl implements ProcessCustomerOrder {

    @Autowired
    private OrderCustomerService orderCustomerService;

    @Autowired
    private EmailClient emailClient;

    @Override
    public void processCustomerOrder(OrderCustomer orderCustomer) {
        orderCustomer.setOrderTime(new Date());

        orderCustomerService.save(orderCustomer);
        try {
            emailClient.send(new EmailMessageOther("Pedido realizado", "O pedido foi realizado com sucesso!", "aurindo@gmail.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
