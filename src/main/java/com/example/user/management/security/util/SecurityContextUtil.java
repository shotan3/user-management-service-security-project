package com.example.user.management.security.util;

import com.example.user.management.security.principal.CustomAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class SecurityContextUtil {

    public static Optional<String> getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName);
    }

    public static void removeAuthenticatedUser() {
        SecurityContextHolder.clearContext();
    }

    public static void saveAuthenticatedUser(UserDetails userDetails) {
        CustomAuthentication authentication = new CustomAuthentication(userDetails);
        authentication.setAuthenticated(true);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

}
