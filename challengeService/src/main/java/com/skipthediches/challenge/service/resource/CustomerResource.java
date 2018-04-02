package com.skipthediches.challenge.service.resource;

import com.skipthediches.challenge.service.entity.Customer;
import com.skipthediches.challenge.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(
            @Valid @RequestBody Customer customer) throws Exception {

        Customer customerSaved = customerService.save(customer);

        URI url =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(customerSaved.getId().toString()).build().toUri();

        return ResponseEntity.created(url).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Customer>> findAll() {
        Iterable<Customer> customers = customerService.findAll();

        return ResponseEntity.ok(customers);
    }
}
