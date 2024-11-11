package com.example.demo6.service;

import com.example.demo6.model.Chef;
import com.example.demo6.repository.chefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class chefService {
    @Autowired
    AuthenticationManager authmanager;
    @Autowired
    chefRepository chefrepo;
    @Autowired
    jwtService jwtserv;
    BCryptPasswordEncoder encoder;
    chefService(){
        this.encoder=new BCryptPasswordEncoder(12);
    }


    public ResponseEntity<HashMap<String, String>> createChef(Chef requestchef) {
    HashMap<String,String> ret=new HashMap<>();
    if(requestchef.getChefName()==null || requestchef.getChefPassword()==null){
        ret.put("message","provide chefName and chefPassowrd");
        return ResponseEntity.ok(ret);
    }
    Chef exisitingchef=chefrepo.findByChefName(requestchef.getChefName());
    if(exisitingchef!=null){
        ret.put("message","user already exists");
        return ResponseEntity.ok(ret);
    }
    Chef chef=new Chef();
    chef.setChefName(requestchef.getChefName());
    chef.setChefPassword(encoder.encode(requestchef.getChefPassword()));
    chef=chefrepo.save(chef);
    System.out.println(chef.getId());
    ret.put("name",chef.getChefName());
    ret.put("token",jwtserv.createToken(""+chef.getId()));
    return ResponseEntity.ok(ret);
    }




    public ResponseEntity<HashMap<String, String>> loginChef(Chef requestchef) {
        HashMap<String,String> ret=new HashMap<>();

        Authentication token=authmanager.authenticate(new UsernamePasswordAuthenticationToken(requestchef.getChefName(),requestchef.getChefPassword()));
        if(token.isAuthenticated()){
            Chef chef=chefrepo.findByChefName(requestchef.getChefName());
            ret.put("name",chef.getChefName());
            ret.put("token",jwtserv.createToken(""+chef.getId()));
            return ResponseEntity.ok(ret);
        }
        ret.put("messsage","login failed");
        return ResponseEntity.ok(ret);

    }



}
