package com.example.demo5.controller;

import com.example.demo5.model.Chef;
import com.example.demo5.service.chefService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class chefController {
    chefService chefserv;
    chefController(chefService chefserv){
    this.chefserv=chefserv;
    }

    @PostMapping("/signup")
    public ResponseEntity<HashMap<String,String>> signup(@RequestBody Chef chef){
        return ResponseEntity.ok(chefserv.signup(chef));
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String,String>> login(@RequestBody Chef chef){
        return ResponseEntity.ok(chefserv.login(chef));
    }
}
