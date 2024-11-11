package com.example.demo6.service;

import com.example.demo6.model.Chef;
import com.example.demo6.model.chefDetails;
import com.example.demo6.repository.chefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class chefDetailsService implements UserDetailsService {

    @Autowired
    chefRepository chefrepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Chef chef=chefrepo.findByChefName(username);
        if(chef==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new chefDetails(chef);
    }
}
