package com.example.user.management.dto;

import com.example.user.management.dto.enums.RoleEnum;
import com.example.user.management.entity.Authority;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RoleDto {

    private RoleEnum role;

    private Set<Authority> authorities;

}
