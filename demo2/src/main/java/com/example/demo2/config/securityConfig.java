package com.example.demo2.config;

import com.example.demo2.service.myUserDetailService;
import org.apache.catalina.Manager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    myUserDetailService userdetailservice;

    securityConfig(myUserDetailService userdetailservice){
        this.userdetailservice=userdetailservice;
    }

    @Bean
    public SecurityFilterChain configHttp(HttpSecurity http) throws Exception {
        http.csrf(customizer->customizer.disable());
        http.authorizeHttpRequests(request->
                request.requestMatchers("signup","login").permitAll()
                        .anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    public AuthenticationProvider configureAuthprovider(){
        DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
        authprovider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        authprovider.setUserDetailsService(userdetailservice);
        return authprovider;
    }

    @Bean
    public AuthenticationManager configureAuthManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
