package com.example.demo2.controller;

import com.example.demo2.model.product;
import com.example.demo2.service.productService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class productController {


    productService productservice;

    productController(productService productservice){
    this.productservice=productservice;
    }

    @GetMapping
    public ResponseEntity<List<product>> getAllProducts(){
        return ResponseEntity.ok(productservice.getAllproducts());
    }

    @PostMapping
    public ResponseEntity<product> addAProduct(@RequestBody product prod){
        return ResponseEntity.ok(productservice.addAProduct(prod));
    }


}
