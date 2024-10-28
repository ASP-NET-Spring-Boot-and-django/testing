package com.example.demo3.service;

import com.example.demo3.model.buyer;
import com.example.demo3.model.buyerPrincipal;
import com.example.demo3.repository.buyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    buyerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        buyer buyerinstance=repo.findByBuyer(username);
        if(buyerinstance==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new buyerPrincipal(buyerinstance);
    }
}
