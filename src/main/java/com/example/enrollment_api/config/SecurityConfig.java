package com.example.enrollment_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disabled for simple API testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/students/**", "/courses/**").permitAll() // GET endpoints are public [cite: 74]
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated() // Protects POST, PUT, DELETE [cite: 65-68]
            )
            .httpBasic(withDefaults()); 
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
            .username("admin")
            .password("{noop}password") // {noop} means plain text password for easy testing
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
