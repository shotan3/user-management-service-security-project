package com.example.user.management.service.impl;

import com.example.user.management.dto.request.LoginRequest;
import com.example.user.management.dto.request.LogoutRequest;
import com.example.user.management.dto.request.RefreshRequest;
import com.example.user.management.dto.response.LoginResponse;
import com.example.user.management.entity.User;
import com.example.user.management.entity.UserSecret;
import com.example.user.management.exception.custom.ResourceNotFoundException;
import com.example.user.management.exception.custom.UnauthorizedRequestException;
import com.example.user.management.repository.UserRepositoryExtended;
import com.example.user.management.security.service.JwtAuthService;
import com.example.user.management.service.AuthService;
import com.example.user.management.util.validators.PasswordUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static com.example.user.management.security.util.SecurityContextUtil.removeAuthenticatedUser;
import static com.example.user.management.security.util.SecurityContextUtil.saveAuthenticatedUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepositoryExtended repository;
    private final PasswordUtilService passwordUtilService;
    private final JwtAuthService authService;
    private final StringRedisTemplate redisTemplate;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = getUser(request.getEmail());
        UserSecret userSecret = getUserSecret(user.getUserUUID());

        if (passwordUtilService.matchesPassword(request.getPassword(), userSecret.getCurrPassword())) {
            request.setPassword("");
            String authToken = authService.generateAuthToken(request.getEmail());
            String refreshToken = authService.generateRefreshToken(request.getEmail());
            saveAuthenticatedUser(authService.getUserDetails(authToken));
            return new LoginResponse(authToken, refreshToken);
        }
        String msg = String.format("Incorrect password for username=%s", request.getEmail());
        log.error(msg);
        throw new UnauthorizedRequestException(msg);
    }

    //TODO: Add validation for refresh token
    @Override
    public LoginResponse refresh(RefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String email = authService.getUserDetails(refreshToken).getUsername();
        String authToken = authService.generateAuthToken(email);
        return new LoginResponse(authToken, refreshToken);
    }

    @Override
    public void logout(LogoutRequest request) {
        blacklistTokens(request.getAuthToken(), request.getRefreshToken());
        removeAuthenticatedUser();
    }

    private User getUser(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> {
                    String msg = String.format("User with username=%s does not exist!", email);
                    log.error(msg);
                    return new ResourceNotFoundException(msg);
                });
    }

    private UserSecret getUserSecret(UUID uuid) {
        return repository.findUserSecretByUserUuid(uuid)
                .orElseThrow(() -> {
                    String msg = String.format("Unable to get secret data for %s!", uuid);
                    log.error(msg);
                    return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, msg);
                });
    }

    private void blacklistTokens(String authToken, String refreshToken) {
        redisTemplate.opsForValue().set(authToken, "");
        redisTemplate.opsForValue().set(refreshToken, "");
    }

}
