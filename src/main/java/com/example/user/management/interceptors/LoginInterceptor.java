package com.example.user.management.interceptors;

import com.example.user.management.entity.LoginHistory;
import com.example.user.management.entity.User;
import com.example.user.management.repository.LoginHistoryRepository;
import com.example.user.management.repository.UserRepositoryExtended;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.ZonedDateTime;
import java.util.Optional;

import static com.example.user.management.security.consts.PublicEndpoints.LOGIN_ENDPOINT;
import static com.example.user.management.security.util.SecurityContextUtil.getAuthenticatedUsername;

@Service
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final UserRepositoryExtended userRepository;
    private final LoginHistoryRepository loginHistoryRepository;


    //I know its terrible place to do that, just wanted to learn interceptors
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        if (!request.getRequestURI().matches(String.format("(.*)%s$", LOGIN_ENDPOINT))) return;
        Optional<String> authenticatedUsername = getAuthenticatedUsername();
        if (authenticatedUsername.isPresent()) {
            String username = authenticatedUsername.get();
            Optional<User> userOptional = userRepository.findByEmailOrContactPhone(username);
            if (userOptional.isPresent()) {
                LoginHistory loginHistory = LoginHistory.builder()
                        .loginTime(ZonedDateTime.now())
                        .loginStatusCode(response.getStatus())
                        .ipAddress(request.getRemoteAddr())
                        .user(userOptional.get())
                        .build();
                loginHistoryRepository.save(loginHistory);
            }

        }
    }

}
