package com.example.demo4.service;


import com.example.demo4.model.customer;
import com.example.demo4.model.customerDetails;
import com.example.demo4.repository.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customerDetailsService implements UserDetailsService {

    @Autowired
    customerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        customer cust=repo.findByName(username);
        if(cust==null){
            throw new UsernameNotFoundException("customer not found");
        }
        return new customerDetails(cust);
    }
}
