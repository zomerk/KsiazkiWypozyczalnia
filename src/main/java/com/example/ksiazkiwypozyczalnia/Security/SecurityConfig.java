package com.example.ksiazkiwypozyczalnia.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    AuthenticationEntryPoint restAuthenticationEntryPoint;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/api/public/**",
            "/api/public/authenticate",
            "/actuator/*",
            "/swagger-ui/**"
    };

    public SecurityConfig(AuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
               .httpBasic(withDefaults())
                //https://www.baeldung.com/spring-redirect-after-login
                .formLogin(withDefaults())
//                .exceptionHandling(handing -> handing
//                        .authenticationEntryPoint(restAuthenticationEntryPoint) // Handles auth error
//                )
//                 //Default Basic auth config
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers("/admin").hasAnyRole("ADMIN")
                        .requestMatchers("/user/api/UserName").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/articlerent/api/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/bookrent/api/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()



                )
                .logout((logout) -> logout.
                        logoutUrl("/logout")
                        .logoutSuccessUrl("/") // przekierowaniw po wylogowaniu
                        //https://docs.spring.io/spring-security/reference/6.0/servlet/authentication/logout.html
                )

        ;

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new SecurityContextLogoutHandler();
    }

}
