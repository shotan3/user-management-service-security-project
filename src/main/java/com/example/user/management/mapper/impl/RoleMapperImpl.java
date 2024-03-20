package com.example.user.management.mapper.impl;

import com.example.user.management.dto.RoleDto;
import com.example.user.management.entity.Role;
import com.example.user.management.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperImpl implements RoleMapper {
    @Override
    public Role mapToRole(RoleDto role) {
        if (role == null) return null;
        return Role.builder()
                .role(role.getRole())
                .authorities(role.getAuthorities())
                .build();
    }

    @Override
    public RoleDto mapToRoleDto(Role role) {
        if (role == null) return null;
        return RoleDto.builder()
                .role(role.getRole())
                .authorities(role.getAuthorities())
                .build();
    }
}
