package com.example.ksiazkiwypozyczalnia.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    AuthenticationEntryPoint restAuthenticationEntryPoint;

    public SecurityConfig(AuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //.httpBasic(Customizer.withDefaults())
                //.exceptionHandling(handing -> handing
                //        .authenticationEntryPoint(restAuthenticationEntryPoint) // Handles auth error
                //)
                // Default Basic auth config
                .csrf(configurer -> configurer.disable())
                // for POST requests via Postman
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.POST, "/api/register").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/books/add").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/books/add_List").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"books/borrow").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET,"/books/user").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
        ;

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
