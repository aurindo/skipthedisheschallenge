package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.OrderCustomerStatusEnum;
import com.skipthediches.challenge.service.repository.OrderCustomerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderCustomerServiceImpl implements OrderCustomerService {

    @Autowired
    private OrderCustomerRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public OrderCustomer sendToQueue(final OrderCustomer orderCustomer) {

        this.rabbitTemplate.convertAndSend("remotingQueue", orderCustomer);
        orderCustomer.setStatus(OrderCustomerStatusEnum.SENDING_TO_RESTAURANT);

        return orderCustomer;
    }

    @Override
    public OrderCustomer save(final OrderCustomer orderCustomer) throws Exception {

        if (orderCustomer.getId() == null) {
            orderCustomer.setStatus(OrderCustomerStatusEnum.WAITTING_RESTAURANT);
        }

        return orderRepository.save(orderCustomer);
    }

    @Override
    public Optional<OrderCustomer> findById(final Long id) {

        return orderRepository.findById(id);
    }

    @Override
    public void cancelOrderCustomer(final Long id) throws Exception {

        OrderCustomer orderCustomer = this.findById(id).orElseThrow(() -> new Exception(""));

        orderCustomer.setStatus(OrderCustomerStatusEnum.CANCELED);

        orderRepository.save(orderCustomer);
    }

    @Override
    public OrderCustomerStatusEnum findOrderStatus(final Long id) throws Exception {

        OrderCustomer orderCustomer = this.findById(id).orElseThrow(() -> new Exception(""));

        return orderCustomer.getStatus();
    }

}
