package com.example.user.management.service;

import com.example.user.management.dto.request.LoginRequest;
import com.example.user.management.dto.request.LogoutRequest;
import com.example.user.management.dto.request.RefreshRequest;
import com.example.user.management.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    LoginResponse refresh(RefreshRequest request);

    void logout(LogoutRequest request);

}
