package com.skipthediches.challenge.service.resource;

import com.skipthediches.challenge.service.entity.OrderCustomer;
import com.skipthediches.challenge.service.entity.OrderCustomerStatusEnum;
import com.skipthediches.challenge.service.service.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/customerOrders")
public class OrderCustomerResource {

    @Autowired
    private OrderCustomerService orderCustomerService;

    @PostMapping
    public ResponseEntity<OrderCustomer> saveOrder(
            @Valid @RequestBody OrderCustomer orderCustomer) throws Exception {

        OrderCustomer orderCustomerSaved = orderCustomerService.save(orderCustomer);

        URI url =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(orderCustomerSaved.getId().toString()).build().toUri();

        return ResponseEntity.created(url).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderCustomer> findById(
            @PathVariable(value = "id", required = true)Long id
    ) {
        OrderCustomer orderCustomer = orderCustomerService.findById(id);
        return new ResponseEntity<OrderCustomer>(orderCustomer, HttpStatus.OK);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<OrderCustomerStatusEnum> getCustomerOrderStatus(
            @PathVariable(value = "id", required = true)Long id
    ) {
        OrderCustomerStatusEnum status = orderCustomerService.findOrderStatus(id);
        return new ResponseEntity<OrderCustomerStatusEnum>(status, HttpStatus.OK);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderCustomer> cancelOrder(
            @PathVariable(value = "id", required = true)Long orderCustomerId){

        orderCustomerService.cancelOrderCustomer(orderCustomerId);

        return new ResponseEntity<OrderCustomer>(HttpStatus.NO_CONTENT);
    }

}
