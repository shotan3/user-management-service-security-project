package com.example.user.management.mapper;

import com.example.user.management.dto.RoleDto;
import com.example.user.management.entity.Role;

public interface RoleMapper {

    Role mapToRole(RoleDto role);

    RoleDto mapToRoleDto(Role role);

}
