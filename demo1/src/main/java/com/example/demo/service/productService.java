package com.example.demo.service;

import com.example.demo.model.product;
import com.example.demo.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    @Autowired
    productRepository productrepository;

    public List<product> getAllProducts(){
        return productrepository.findAll();
    }
    public product createProduct(product prod){
        return productrepository.save(prod);
    }
}
