package com.example.demo.service;

import com.example.demo.model.customer;
import com.example.demo.model.customerPrincipal;
import com.example.demo.repository.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class myUserDetailService implements UserDetailsService {

    @Autowired
    customerRepository customerRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        customer cust=customerRepo.findByName(username);
        if(cust==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new customerPrincipal(cust);
    }
}
