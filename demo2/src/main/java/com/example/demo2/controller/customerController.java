package com.example.demo2.controller;


import com.example.demo2.model.customer;
import com.example.demo2.service.customerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class customerController {

    customerService customerservice;
    public customerController(customerService customerservice){
        this.customerservice=customerservice;
    }

    @PostMapping("/signup")
    public ResponseEntity<customer> createCustomer(@RequestBody customer cust){
        return ResponseEntity.ok(customerservice.createCustomer(cust));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody customer cust){
        return ResponseEntity.ok(customerservice.loginCustomer(cust));
    }

}
