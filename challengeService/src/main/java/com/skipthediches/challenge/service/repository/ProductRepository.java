package com.skipthediches.challenge.service.repository;

import com.skipthediches.challenge.service.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
