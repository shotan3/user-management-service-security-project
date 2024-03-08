package com.example.user.management.dto;

import com.example.user.management.entity.Authority;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RoleDto {

    private String roleName;

    private Set<Authority> authorities;

}
