package com.example.user.management.service.security;

import com.example.user.management.dto.RoleDto;
import com.example.user.management.dto.enums.RoleEnum;
import com.example.user.management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Optional<RoleDto> getRole(RoleEnum role) {
        return repository.findRoleByRoleName(role)
                .map(r -> RoleDto.builder()
                        .role(r.getRole())
                        .authorities(r.getAuthorities())
                        .build());
    }

}
