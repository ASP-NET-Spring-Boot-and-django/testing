package com.example.demo6.controller;

import com.example.demo6.model.Chef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo6.service.chefService;
import java.util.HashMap;

@RestController
public class chefController {

    @Autowired
    chefService chefserv;
    @PostMapping("/signup")
    public ResponseEntity<HashMap<String,String>> signupChef(@RequestBody Chef chef){
        return chefserv.createChef(chef);
    }


    @PostMapping("/login")
    public ResponseEntity<HashMap<String,String>> loginChef(@RequestBody Chef chef){
        return chefserv.loginChef(chef);
    }
}
