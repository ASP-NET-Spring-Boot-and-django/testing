package com.example.demo.Service;

import com.example.demo.Repository.userRepository;
import com.example.demo.model.Users;
import com.example.demo.model.userPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
@Service
public class myUserDetailsService implements UserDetailsService {

    @Autowired
    userRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user=userRepo.findByName(username);
        if(user==null){
            System.out.println("user not found");

            throw new UsernameNotFoundException("user not found");
        }
        return new userPrincipal(user);
    }
}
