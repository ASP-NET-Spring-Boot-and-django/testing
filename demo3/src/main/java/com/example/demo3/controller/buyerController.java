package com.example.demo3.controller;

import com.example.demo3.model.buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo3.service.buyerService;

@RestController
public class buyerController {
    @Autowired
    buyerService buyerService;



    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody buyer buyer){
        return ResponseEntity.ok(buyerService.signup(buyer));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody buyer buyer){
        return ResponseEntity.ok(buyerService.login(buyer));
    }
}
