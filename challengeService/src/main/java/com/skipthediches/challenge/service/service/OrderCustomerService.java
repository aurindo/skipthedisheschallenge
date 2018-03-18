package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.OrderCustomerStatusEnum;

public interface OrderCustomerService {

    OrderCustomer save(final OrderCustomer orderCustomer) throws Exception;

    OrderCustomer sendToQueue(final OrderCustomer orderCustomer) throws Exception;

    void cancelOrderCustomer(Long orderCustomerId);

    OrderCustomerStatusEnum findOrderStatus(Long orderCustomerId);

    OrderCustomer findById(Long orderCustomerId);
}
