package com.example.demo4.controller;

import com.example.demo4.model.customer;
import com.example.demo4.model.product;
import com.example.demo4.service.productService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class productController {

    @Autowired
    productService prodserv;

    @GetMapping
    public ResponseEntity<List<product>> getAllProducts(HttpServletRequest request){

        return ResponseEntity.ok(prodserv.getAllProducts((String)request.getAttribute("username")));
    }

    @PostMapping
    public ResponseEntity<product> createAProduct(@RequestBody product product,HttpServletRequest request){
        return ResponseEntity.ok(prodserv.createAProduct(product,(String)request.getAttribute("username")));
    }
}
