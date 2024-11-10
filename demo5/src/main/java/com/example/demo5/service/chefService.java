package com.example.demo5.service;

import com.example.demo5.model.Chef;
import com.example.demo5.repository.chefRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class chefService {
    chefRepository chefrepo;
    BCryptPasswordEncoder encrypter;
    jwtService jwtServ;
    AuthenticationManager authmangaer;
    chefService(chefRepository chefrepo,jwtService jwtServ,AuthenticationManager authmangaer){
        this.chefrepo=chefrepo;
        encrypter=new BCryptPasswordEncoder(12);
        this.jwtServ=jwtServ;
        this.authmangaer=authmangaer;
    }

    public HashMap<String, String> signup(Chef requestchef) {
        HashMap<String,String> ret=new HashMap<>();
        Chef ch=new Chef();
        ch.setChefname(requestchef.getChefname());
        ch.setChefpassword(encrypter.encode(requestchef.getChefpassword()));
        ch=chefrepo.save(ch);
        String token=jwtServ.generateToken(""+ch.getId());
        ret.put("chefname",ch.getChefname());
        ret.put("token",token);

        return ret;
    }


    public HashMap<String, String> login(Chef requestchef) {
        HashMap<String,String> ret=new HashMap<>();

        Authentication auth=authmangaer.authenticate(new UsernamePasswordAuthenticationToken(requestchef.getChefname(),requestchef.getChefpassword()));
        if(auth.isAuthenticated()){
            Chef chef=chefrepo.findByChefname(requestchef.getChefname());
            String token;
            token = jwtServ.generateToken(""+chef.getId());
            ret.put("chefname",chef.getChefname());
            ret.put("token",token);
            return ret;
        }
        return ret;
    }
}
