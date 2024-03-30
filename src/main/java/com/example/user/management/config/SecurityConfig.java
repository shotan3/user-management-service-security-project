package com.example.user.management.config;

import com.example.user.management.security.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.user.management.security.consts.PublicEndpoints.LOGIN_ENDPOINT;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) //Todo: Needs reconfiguration
                .cors(AbstractHttpConfigurer::disable) //Todo: Needs reconfiguration
                .addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(LOGIN_ENDPOINT).permitAll();
                    authorize.anyRequest().permitAll();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(
            @Value("${user.config.password.bcrypt-strength:10}") int strength,
            @Value("${user.config.password.bcrypt-version:$2A}") String version
    ) {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.valueOf(version), strength);
    }

}
