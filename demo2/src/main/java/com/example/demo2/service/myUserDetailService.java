package com.example.demo2.service;

import com.example.demo2.model.customerPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo2.repository.customerRepo;
import com.example.demo2.model.customer;

@Service
public class myUserDetailService implements UserDetailsService {

    customerRepo repo;
    myUserDetailService(customerRepo repo){
    this.repo=repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        customer cust=repo.findByName(username);
        if(cust==null){
            throw new UsernameNotFoundException("user not found");
        }

        return new customerPrincipal(cust);
    }
}
