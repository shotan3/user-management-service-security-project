package com.example.user.management.controller;

import com.example.user.management.dto.RoleDto;
import com.example.user.management.dto.enums.RoleEnum;
import com.example.user.management.exception.custom.ResourceNotFoundException;
import com.example.user.management.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/role")
public class RoleController {

    private final RoleService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RoleDto> getAuthoritiesForRole(@RequestParam RoleEnum roleName) {
        return service.getRole(roleName)
                .map(ResponseEntity::ok)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
