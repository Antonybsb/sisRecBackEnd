package com.example.sisrec.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
    @Autowired
    private SecurityFilterChain securityFilterChain;


    @Bean
    public org.springframework.security.web.SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registro").permitAll()

                        .requestMatchers(HttpMethod.POST,"/reclamacoes").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET,"/reclamacoes").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/reclamacoes").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/reclamacoes").permitAll()

                        .requestMatchers(HttpMethod.POST,"/usuario").permitAll()
                        .requestMatchers(HttpMethod.GET,"/usuario").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/usuario").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/usuario").permitAll()
                        .anyRequest().authenticated()

                )
                .addFilterBefore(securityFilterChain, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Encoda a senha no banco de dados
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}