package com.example.demo2.service;

import com.example.demo2.model.customer;
import com.example.demo2.repository.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class customerService {

    @Autowired
    customerRepo repo;
    @Autowired
    AuthenticationManager authmanager;


    BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder(10);

    public customer createCustomer(customer cust) {
        cust.toString();
        cust.setPassword(bcrypt.encode(cust.getPassword()));
        return repo.save(cust);

    }

    public String loginCustomer(customer cust) {
        Authentication auth=authmanager.authenticate(new UsernamePasswordAuthenticationToken(cust.getName(),cust.getPassword()));
        if(auth.isAuthenticated())
        {
            return "success";
        }
        return "fail";
    }
}
