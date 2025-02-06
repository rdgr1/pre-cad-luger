package br.com.luger.dev.precad.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class Configurations {


    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("PasswordEncoder initialized");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        System.out.println("AuthenticationProvider initialized");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        System.out.println("AuthenticationManager initialized");
        return new ProviderManager(Collections.singletonList(authenticationProvider(userDetailsService)));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        System.out.println("RestTemplate initialized");
        return builder.build();
    }


}

