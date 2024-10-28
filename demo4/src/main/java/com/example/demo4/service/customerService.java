package com.example.demo4.service;

import com.example.demo4.model.customer;
import com.example.demo4.repository.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class customerService {
    @Autowired
    AuthenticationManager auth;
    @Autowired
    customerRepo repo;
    @Autowired
    jwtService jwt;
    BCryptPasswordEncoder bp=new BCryptPasswordEncoder(12);
    public String signup(customer cust) {
        customer existingCustomer=repo.findByName(cust.getName());
        if(existingCustomer==null){
            cust.setPassword(bp.encode(cust.getPassword()));
            repo.save(cust);  return "user created successfully";
        }
        return "customer already exists";

    }

    public Map<String, Object> login(customer cust) {

        Authentication authObject=auth.authenticate(new UsernamePasswordAuthenticationToken(cust.getName(),cust.getPassword()));
        Map<String, Object> response = new HashMap<>();
        if(authObject.isAuthenticated()){
            response.put("name",cust.getName());
            response.put("token",jwt.generateToken(cust.getName()));
            return response;
        }
        response.put("message","login failed");
        return response;

    }
}
