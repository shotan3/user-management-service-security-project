package com.example.user.management.controller.view;


import com.example.user.management.dto.request.LogoutRequest;
import com.example.user.management.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthViewController {

    private final AuthService service;

    @PostMapping(value = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String logout(@RequestBody LogoutRequest request) {
        service.logout(request);
        return "login";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

}
