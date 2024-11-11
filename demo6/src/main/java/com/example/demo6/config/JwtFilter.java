package com.example.demo6.config;


import com.example.demo6.model.Chef;
import com.example.demo6.model.chefDetails;
import com.example.demo6.repository.chefRepository;
import com.example.demo6.service.jwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    jwtService jwtserv;
    @Autowired
    chefRepository chefrepo;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        String token=null;
        String userId=null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            userId=jwtserv.getSubject(token);
        }

        if(token!=null && userId!=null){
            if(jwtserv.validateToken(token)){
                Long userIDLong=Long.parseLong(userId);
                Optional<Chef> chef=chefrepo.findById(userIDLong);
                if(!chef.isEmpty()){
                    request.setAttribute("userId",userIDLong);
                    chefDetails chefdet=new chefDetails(chef.get());
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(chefdet,null,chefdet.getAuthorities()));
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
