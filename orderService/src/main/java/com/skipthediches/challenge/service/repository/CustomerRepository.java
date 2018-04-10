package com.skipthediches.challenge.service.repository;

import com.skipthediches.challenge.service.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByLoginAndPassword(String login, String password);

    Customer findByLogin(String login);

}
