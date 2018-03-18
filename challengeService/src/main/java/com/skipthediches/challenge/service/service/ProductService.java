package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.Product;

public interface ProductService {

    Iterable<Product> findAll();

    Product findById(Long productId);
}