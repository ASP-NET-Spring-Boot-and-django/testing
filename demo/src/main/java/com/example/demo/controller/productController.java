package com.example.demo.controller;

import com.example.demo.Service.productService;
import com.example.demo.model.product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class productController {
    productService productservice;
    productController(productService productservice){
        this.productservice=productservice;
    }

    @GetMapping("/products")
    public ResponseEntity<List<product>> getProducts(){
        return ResponseEntity.ok(productservice.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<product> addProduct(@RequestBody product product){
        return ResponseEntity.ok(productservice.addProduct(product));
    }
}
