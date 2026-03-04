/* Copyright 2026 XYZ Retail */
package com.xyz.retail.infrastructure.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers("/api/v1/products/**")
                    .permitAll()
                    .requestMatchers("/api/v1/cart/**")
                    .permitAll()
                    .requestMatchers("/api/v1/orders/{orderId}")
                    .hasAnyRole("EMPLOYEE", "MANAGER")
                    .requestMatchers("/api/v1/orders")
                    .permitAll()
                    .requestMatchers("/api/v1/reports/**")
                    .hasRole("MANAGER")
                    .anyRequest()
                    .authenticated())
        .httpBasic(withDefaults())
        .csrf(csrf -> csrf.disable()); // Disable CSRF for API usage

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    // Create sample users with roles
    UserDetails user =
        User.builder()
            .username("user123")
            .password(passwordEncoder.encode("password123"))
            .roles("USER")
            .build();

    UserDetails employee =
        User.builder()
            .username("employee1")
            .password(passwordEncoder.encode("emp123"))
            .roles("EMPLOYEE")
            .build();

    UserDetails manager =
        User.builder()
            .username("manager1")
            .password(passwordEncoder.encode("mgr123"))
            .roles("MANAGER")
            .build();

    return new InMemoryUserDetailsManager(user, employee, manager);
  }
}
