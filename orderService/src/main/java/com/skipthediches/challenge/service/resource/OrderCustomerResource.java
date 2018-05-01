package com.skipthediches.challenge.service.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.enumerators.OrderCustomerStatusEnum;
import com.skipthediches.challenge.service.service.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;

@RestController
@RequestMapping("/customerOrders")
public class OrderCustomerResource {

    @Autowired
    private OrderCustomerService orderCustomerService;

    @PostMapping
    @HystrixCommand(fallbackMethod = "saveOrderCircuitBreaker")
    public ResponseEntity<OrderCustomer> saveOrder(
            @Valid @RequestBody OrderCustomer orderCustomer) throws Exception {

        OrderCustomer orderCustomerSaved = orderCustomerService.sendToQueue(orderCustomer);

        URI url =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/").build().toUri();

        return ResponseEntity.created(url).build();
    }

    public ResponseEntity<OrderCustomer> saveOrderCircuitBreaker(
            @Valid @RequestBody OrderCustomer orderCustomer) throws Exception {

        return ResponseEntity.created(new URI("???")).build();

    }

    @GetMapping
    @HystrixCommand(fallbackMethod = "findAllCircuitBreaker")
    public ResponseEntity<Iterable<OrderCustomer>> findAll() {

        ResponseEntity<Iterable<OrderCustomer>> responseEntity;

        try {
            responseEntity = ResponseEntity.ok(orderCustomerService.findAll());
        } catch (Exception e) {
            responseEntity = ResponseEntity.notFound().build();
        }

        return responseEntity;
    }

    public ResponseEntity<Iterable<OrderCustomer>> findAllCircuitBreaker() {
        Iterable<OrderCustomer> resultFake = new HashSet<>();
        return ResponseEntity.ok(resultFake);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderCustomer> findById(
            @PathVariable(value = "id", required = true)Long id) {

        ResponseEntity<OrderCustomer> responseEntity;

        try {
            responseEntity = ResponseEntity.ok(orderCustomerService.findById(id));
        } catch (Exception e) {
            responseEntity = ResponseEntity.notFound().build();
        }

        return responseEntity;
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<OrderCustomerStatusEnum> getCustomerOrderStatus(
            @PathVariable(value = "id", required = true)Long id
    ) throws Exception {
        OrderCustomerStatusEnum status = orderCustomerService.findOrderStatus(id);

        return ResponseEntity.ok(status);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderCustomer> cancelOrder(
            @PathVariable(value = "id", required = true)Long orderCustomerId) throws Exception {

        orderCustomerService.cancelOrderCustomer(orderCustomerId);

        return ResponseEntity.noContent().build();
    }

}
