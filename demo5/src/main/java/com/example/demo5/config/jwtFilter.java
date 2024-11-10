package com.example.demo5.config;

import com.example.demo5.model.Chef;
import com.example.demo5.repository.chefRepository;
import com.example.demo5.service.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo5.service.jwtService;
import java.io.IOException;
import java.util.Optional;

@Component
public class jwtFilter extends OncePerRequestFilter {
    @Autowired
    jwtService jwtService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    chefRepository chefrepo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        String token=null;
        long id=-1;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            String idValue=jwtService.extractSubject(token);
            if(idValue!=null){
                id=Long.parseLong(idValue);
            }
        }
        if(id!=-1 && !jwtService.isTokenExpired(token) && SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println(id);
            Optional<Chef> chef=chefrepo.findById(id);

            if(chef.isPresent()){
                UserDetails userDetails = userDetailService.loadUserByUsername(chef.get().getChefname());
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }


        filterChain.doFilter(request,response);
    }
}
