package com.example.demo3.service;

import com.example.demo3.model.buyer;
import com.example.demo3.repository.buyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class buyerService {

    @Autowired
    AuthenticationManager authmanager;

    @Autowired
    buyerRepo repo;
    private final BCryptPasswordEncoder bp;
    buyerService(){
        this.bp=new BCryptPasswordEncoder(12);
    }

    public String login(buyer buyer) {
        System.out.println(buyer.getBuyer());
       Authentication auth=authmanager.authenticate(new UsernamePasswordAuthenticationToken(buyer.getBuyer(),buyer.getPassword()));
       if(auth.isAuthenticated()){
           return "success";
       }
       return "failure";
    }

    public String signup(buyer buyer) {

        buyer exists=repo.findByBuyer(buyer.getBuyer());
        if(exists==null){
            buyer.setPassword(bp.encode(buyer.getPassword()));
            repo.save(buyer);
            return "new user created successfully";
        }
        else {
            return "user already exist";
        }
    }
}
