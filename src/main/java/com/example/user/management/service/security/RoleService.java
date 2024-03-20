package com.example.user.management.service.security;

import com.example.user.management.dto.RoleDto;
import com.example.user.management.dto.enums.RoleEnum;
import com.example.user.management.mapper.RoleMapper;
import com.example.user.management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;


    public Optional<RoleDto> getRole(RoleEnum role) {
        return repository.findRoleByRoleName(role)
                .map(mapper::mapToRoleDto);
    }

}
