package com.example.demo5.service;

import com.example.demo5.model.Chef;
import com.example.demo5.model.ChefDetails;
import com.example.demo5.repository.chefRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    chefRepository chef;
    public UserDetailService(chefRepository chef){
        this.chef=chef;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Chef chefuser=chef.findByChefname(username);
        if(chefuser==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new ChefDetails(chefuser);
    }
}
