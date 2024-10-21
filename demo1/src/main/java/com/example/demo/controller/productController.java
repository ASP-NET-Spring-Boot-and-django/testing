package com.example.demo.controller;

import com.example.demo.model.product;
import com.example.demo.service.productService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class productController {

    productService prodService;
    productController(productService prodService){
        this.prodService=prodService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<product>> getProducts(){

        return ResponseEntity.ok(prodService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<product> createProduct(@RequestBody product prod){
        return ResponseEntity.ok(prodService.createProduct(prod));
    }
}
