package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.demo.service.myUserDetailService;


@Configuration
@EnableWebSecurity
public class mainconfig {

    @Autowired
    myUserDetailService myUserDetailService;

    @Bean
    public SecurityFilterChain configureCSRF(HttpSecurity http) throws Exception {
        http.csrf(customizer->customizer.disable());
        http.authorizeHttpRequests(request->request.anyRequest().permitAll());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public AuthenticationProvider configureAuthProvider(){
        DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
        authprovider.setUserDetailsService(myUserDetailService);
        authprovider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return authprovider;

    }
}
