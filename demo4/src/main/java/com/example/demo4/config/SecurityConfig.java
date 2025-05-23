package com.example.demo4.config;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo4.service.customerDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    customerDetailsService customerDetailsService;
    @Bean
    public SecurityFilterChain configureFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer->customizer.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(request->
                        request.requestMatchers("login","signup").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthenticationProvider configureAuthProvider(){
        DaoAuthenticationProvider dap=new DaoAuthenticationProvider();
        dap.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        dap.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        dap.setUserDetailsService(customerDetailsService);
        return dap;
    }

    @Bean
    public AuthenticationManager configureAuthManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
