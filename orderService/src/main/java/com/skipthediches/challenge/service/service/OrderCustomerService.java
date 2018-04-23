package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.enumerators.OrderCustomerStatusEnum;
import com.skipthediches.challenge.service.exception.AppEntityNotFoundException;

public interface OrderCustomerService {

    OrderCustomer save(final OrderCustomer orderCustomer);

    OrderCustomer sendToQueue(final OrderCustomer orderCustomer);

    Iterable<OrderCustomer> findAll();

    void cancelOrderCustomer(Long orderCustomerId) throws AppEntityNotFoundException;

    OrderCustomerStatusEnum findOrderStatus(Long orderCustomerId) throws AppEntityNotFoundException;

    OrderCustomer findById(Long orderCustomerId) throws AppEntityNotFoundException;
}
