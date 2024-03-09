package com.example.user.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
@AllArgsConstructor
public class AuthorityDto implements GrantedAuthority {

    private String authority;

}
