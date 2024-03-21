package com.example.user.management.controller;

import com.example.user.management.dto.LoginHistoryDto;
import com.example.user.management.exception.custom.ResourceNotFoundException;
import com.example.user.management.service.LoginHistoryService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginHistoryController {

    private final LoginHistoryService service;

    @GetMapping(value = "/users/{user_uuid}/login-history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginHistoryDto> getLoginHistory(
            @PathVariable(name = "user_uuid")
            @Parameter(example = "a0d1c31a-83aa-43ae-b4a3-2c04146ba0df") UUID userUuid) {
        return service.getLoginHistory(userUuid)
                .map(ResponseEntity::ok)
                .orElseThrow(ResourceNotFoundException::new);
    }

}
