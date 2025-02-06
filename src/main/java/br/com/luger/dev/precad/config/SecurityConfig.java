package br.com.luger.dev.precad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    @Autowired
    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("securityFilterChain passou");
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/mgmt/**").authenticated()
                        .requestMatchers("/admin/**").authenticated()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/").permitAll()
                        .anyRequest().permitAll()
                );


        http.csrf(AbstractHttpConfigurer::disable);
        System.out.println("securityFilterChain line 45");
        http
                .addFilterBefore(
                        securityFilter, UsernamePasswordAuthenticationFilter.class
                );
        System.out.println("securityFilterChain line 50");
        http.sessionManagement(smc -> smc
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        System.out.println("securityFilterChain line 54");
        return http.build();
    }


}