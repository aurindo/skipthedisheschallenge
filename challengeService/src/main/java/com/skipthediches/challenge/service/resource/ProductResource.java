package com.skipthediches.challenge.service.resource;

import com.skipthediches.challenge.service.entity.Product;
import com.skipthediches.challenge.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllUsers() {
        Iterable<Product> products = productService.findAll();

        return new ResponseEntity<Iterable<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(
            @PathVariable(value = "id", required = true)Long productId) {
        Product product = productService.findById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

}
