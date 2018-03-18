package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.Customer;

public interface CustomerService {
    Iterable<Customer> findAll();

    Customer save(Customer customer);
}
