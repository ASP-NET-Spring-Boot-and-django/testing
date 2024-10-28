package com.example.demo4.controller;

import com.example.demo4.model.customer;
import com.example.demo4.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class customerController {
    @Autowired
    customerService custService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody customer cust){
        return ResponseEntity.ok(custService.signup(cust));
    }

    @PostMapping("/login")
    ResponseEntity<Map<String, Object>> login(@RequestBody customer cust){
        return ResponseEntity.ok(custService.login(cust));
    }

}
