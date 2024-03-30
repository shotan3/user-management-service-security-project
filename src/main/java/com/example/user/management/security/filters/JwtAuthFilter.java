package com.example.user.management.security.filters;

import com.example.user.management.exception.custom.UnauthorizedRequestException;
import com.example.user.management.security.service.JwtAuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static com.example.user.management.security.consts.PublicEndpoints.LOGIN_ENDPOINT;
import static com.example.user.management.security.util.SecurityContextUtil.saveAuthenticatedUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtAuthService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (notOnPublicEndpoint(request.getRequestURI())) {
            Optional<String> authorizationToken = Optional.ofNullable(request.getHeader("Authorization"));
            if (authorizationToken.isPresent() && jwtService.notBlackListed(authorizationToken.get())) {
                String jwtToken = parseJwtTokenFromHeader(authorizationToken.get());
                try {
                    jwtService.validateAuthToken(jwtToken);
                    saveAuthenticatedUser(jwtService.getUserDetails(jwtToken));
                    filterChain.doFilter(request, response);
                } catch (ExpiredJwtException e) {
                    log.error("Token expired! Redirecting to login endpoint...");
                    redirectToLoginEndpoint(response);
                } catch (JwtException e) {
                    String msg = "Invalid token! Authorization failed!";
                    log.error(msg);
                    throw new UnauthorizedRequestException(msg);
                }
            } else {
                redirectToLoginEndpoint(response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private String parseJwtTokenFromHeader(String header) {
        return header.replaceAll("Bearer(\\s*)", "");
    }

    private boolean notOnPublicEndpoint(String endpoint) {
        return notOnLoginEndpoint(endpoint);
    }

    private boolean notOnLoginEndpoint(String endpoint) {
        return !endpoint.equals(LOGIN_ENDPOINT);
    }

    private void redirectToLoginEndpoint(HttpServletResponse response) throws IOException {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
        wrapper.setStatus(HttpStatus.FOUND.value());
        wrapper.sendRedirect(LOGIN_ENDPOINT);
        wrapper.flushBuffer();
    }

}
