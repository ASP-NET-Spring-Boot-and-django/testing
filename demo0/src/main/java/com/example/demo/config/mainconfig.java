package com.example.demo.config;

import com.example.demo.Service.myUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class mainconfig {

    myUserDetailsService myUserDetailsService;
    mainconfig(myUserDetailsService myUserDetailsService){
        this.myUserDetailsService=myUserDetailsService;
    }

    @Bean
    public SecurityFilterChain configureHttpSecurity(HttpSecurity http) throws Exception {
        http.csrf(customizer->customizer.disable());
        http.authorizeHttpRequests(customizer->customizer.anyRequest().authenticated());
//        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService configureUserDetails(){
//        UserDetails user1=User.withDefaultPasswordEncoder()
//                .username("vis")
//                .password("v@123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1);
//    }

    @Bean
    public AuthenticationProvider configAuthProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return authenticationProvider;
    }

}

