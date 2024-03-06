package com.example.user.management.controller;

import com.example.user.management.dto.AccountInfoDto;
import com.example.user.management.service.AccountInfoService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountInfoController {

    private final AccountInfoService service;

    @GetMapping("/user/{user_uuid}/account-info")
    public ResponseEntity<AccountInfoDto> getAccountInfo(
            @PathVariable(name = "user_uuid")
            @Parameter(example = "a0d1c31a-83aa-43ae-b4a3-2c04146ba0df") UUID userUuid) {
        return service.getAccountInfoDto(userUuid)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
