package com.skipthediches.challenge.service.resource;

import com.skipthediches.challenge.service.entity.Customer;
import com.skipthediches.challenge.service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

}
