package com.example.demo3.controller;


import com.example.demo3.model.product;
import com.example.demo3.service.productService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class productController {

    private productService prodserv;
    productController(productService prodserv){
        this.prodserv=prodserv;
    }

    @GetMapping
    public ResponseEntity<List<product>> getAllProducts(){
        return ResponseEntity.ok(prodserv.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<product> createAProduct(@RequestBody product product){
        return ResponseEntity.ok(prodserv.createAProduct(product));
    }

}
