package com.example.demo3.service;

import com.example.demo3.model.product;
import com.example.demo3.repository.productRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    productRepo repo;
    productService(productRepo repo){
        this.repo=repo;
    }

    public List<product> getAllProducts() {
        return repo.findAll();
    }

    public product createAProduct(product product) {
        return repo.save(product);
    }
}
