package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.OrderCustomerStatusEnum;
import com.skipthediches.challenge.service.repository.OrderCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderCustomerServiceImpl implements OrderCustomerService {

    @Autowired
    private OrderCustomerRepository orderRepository;

    @Override
    public OrderCustomer save(final OrderCustomer orderCustomer) {
        return orderRepository.save(orderCustomer);
    }

    @Override
    public OrderCustomer findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void cancelOrderCustomer(Long id) {
        OrderCustomer orderCustomer = this.findById(id);

        orderCustomer.setStatus(OrderCustomerStatusEnum.CANCELED);

        orderRepository.save(orderCustomer);
    }

    @Override
    public OrderCustomerStatusEnum findOrderStatus(Long id) {

        OrderCustomer orderCustomer = this.findById(id);

        return orderCustomer.getStatus();
    }


}
