package com.example.demo4.config;

import com.example.demo4.service.customerDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import com.example.demo4.service.jwtService;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    jwtService jwtService;

    @Autowired
    customerDetailsService customerDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader= request.getHeader("Authorization");
        String token=null;
        String username=null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            username=jwtService.getSubject(token);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null && jwtService.verifyToken(token)){
            UserDetails userdetails=customerDetailsService.loadUserByUsername(username);
            request.setAttribute("username",username);
            UsernamePasswordAuthenticationToken authtoken=new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
            authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authtoken);
        }
        filterChain.doFilter(request,response);
    }
}
