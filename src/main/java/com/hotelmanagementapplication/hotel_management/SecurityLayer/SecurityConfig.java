package com.hotelmanagementapplication.hotel_management.SecurityLayer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(withDefaults())
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // PUBLIC
                .requestMatchers("/auth/**").permitAll()

                // USERS -> ADMIN ONLY
                .requestMatchers("/users/**").hasRole("ADMIN")

                // HOTELS
                .requestMatchers(HttpMethod.GET, "/hotels/**").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers("/hotels/**").hasRole("ADMIN")

                // ROOMS
                .requestMatchers(HttpMethod.GET, "/rooms/**").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers("/rooms/**").hasRole("ADMIN")

                // RESERVATIONS
                .requestMatchers("/reservations/**").hasAnyRole("ADMIN", "CUSTOMER")

                // REVIEWS
                .requestMatchers("/reviews/**").hasAnyRole("ADMIN", "CUSTOMER")

                // EVERYTHING ELSE
                .anyRequest().authenticated()
            )

            .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}