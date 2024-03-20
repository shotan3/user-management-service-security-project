package com.example.user.management.controller;

import com.example.user.management.dto.RoleDto;
import com.example.user.management.dto.enums.RoleEnum;
import com.example.user.management.service.security.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/role")
public class RoleController {

    private final RoleService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<RoleDto> getAuthoritiesForRole(@RequestParam RoleEnum roleName) {
        return service.getRole(roleName);
    }
}
