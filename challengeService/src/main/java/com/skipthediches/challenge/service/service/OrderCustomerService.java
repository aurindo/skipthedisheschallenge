package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.OrderCustomerStatusEnum;

import java.util.Optional;

public interface OrderCustomerService {

    OrderCustomer save(final OrderCustomer orderCustomer) throws Exception;

    OrderCustomer sendToQueue(final OrderCustomer orderCustomer) throws Exception;

    void cancelOrderCustomer(Long orderCustomerId) throws Exception;

    OrderCustomerStatusEnum findOrderStatus(Long orderCustomerId) throws Exception;

    Optional<OrderCustomer> findById(Long orderCustomerId);
}
