package com.example.user.management.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
public class AuthorityDto implements GrantedAuthority {

    private String authority;

}
