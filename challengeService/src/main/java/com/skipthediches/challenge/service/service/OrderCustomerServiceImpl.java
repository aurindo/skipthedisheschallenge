package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.enumerators.OrderCustomerStatusEnum;
import com.skipthediches.challenge.service.exception.AppEntityNotFoundException;
import com.skipthediches.challenge.service.repository.OrderCustomerRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;

@Service
public class OrderCustomerServiceImpl implements OrderCustomerService {

    @Autowired
    private OrderCustomerRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public OrderCustomer sendToQueue(final OrderCustomer orderCustomer) {

        this.rabbitTemplate.convertAndSend(queue.getName(), orderCustomer);
        orderCustomer.setStatus(OrderCustomerStatusEnum.SENDING_TO_RESTAURANT);

        return orderCustomer;
    }

    @Override
    public OrderCustomer save(final OrderCustomer orderCustomer) {

        if (orderCustomer.getId() == null) {
            orderCustomer.setStatus(OrderCustomerStatusEnum.WAITTING_RESTAURANT);
        }

        return orderRepository.save(orderCustomer);
    }

    @Override
    public OrderCustomer findById(final Long id) throws AppEntityNotFoundException {
        return orderRepository.findById(id).orElseThrow(
                () -> new AppEntityNotFoundException(OrderCustomer.class, id)
        );
    }

    @Override
    public void cancelOrderCustomer(final Long id) throws AppEntityNotFoundException {

        OrderCustomer orderCustomer = this.findById(id);

        orderCustomer.setStatus(OrderCustomerStatusEnum.CANCELED);

        orderRepository.save(orderCustomer);
    }

    @Override
    public OrderCustomerStatusEnum findOrderStatus(final Long id) throws AppEntityNotFoundException {

        OrderCustomer orderCustomer = this.findById(id);

        return orderCustomer.getStatus();
    }

}
