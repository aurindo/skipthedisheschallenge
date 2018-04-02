package com.skipthediches.challenge.service.service;

import com.skipthediches.challenge.service.entity.Product;
import com.skipthediches.challenge.service.exception.AppEntityNotFoundException;
import com.skipthediches.challenge.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) throws AppEntityNotFoundException {
        return productRepository.findById(productId).orElseThrow(
                () -> new AppEntityNotFoundException(Product.class, productId)
        );
    }

}
