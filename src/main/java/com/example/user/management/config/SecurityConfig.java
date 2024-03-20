package com.example.user.management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(
            @Value("${user.config.password.bcrypt-strength:10}") int strength,
            @Value("${user.config.password.bcrypt-version:$2A}") String version
    ) {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.valueOf(version), strength);
    }

}
