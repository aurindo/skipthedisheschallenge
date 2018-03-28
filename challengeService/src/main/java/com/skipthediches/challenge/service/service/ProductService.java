package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.Product;

import java.util.Optional;

public interface ProductService {

    Iterable<Product> findAll();

    Optional<Product> findById(Long productId);
}
