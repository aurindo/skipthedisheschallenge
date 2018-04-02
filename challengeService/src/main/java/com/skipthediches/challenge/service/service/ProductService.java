package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.Product;
import com.skipthediches.challenge.service.exception.AppEntityNotFoundException;

public interface ProductService {

    Iterable<Product> findAll();

    Product findById(Long productId) throws AppEntityNotFoundException;
}
