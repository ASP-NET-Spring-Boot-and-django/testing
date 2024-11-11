package com.example.demo6.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class firstController {
    @GetMapping("/a")
    public ResponseEntity<String> protectrouteA(HttpServletRequest request){
        return ResponseEntity.ok(""+request.getAttribute("userId"));
    }

    @PostMapping("/b")
    public ResponseEntity<String> protectrouteB(){
        return ResponseEntity.ok("HelloworldB");
    }
}
