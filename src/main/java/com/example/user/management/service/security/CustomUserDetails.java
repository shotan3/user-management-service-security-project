package com.example.user.management.service.security;

import com.example.user.management.dto.AuthorityDto;
import com.example.user.management.dto.UserDto;
import com.example.user.management.dto.UserSecretDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final UserDto user;

    private final UserSecretDto userSecretDto;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user
                .getRole()
                .getAuthorities()
                .stream()
                .map(authority -> new AuthorityDto(authority.getAuthority()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return userSecretDto.getCurrPassword();
    }

    @Override
    public String getUsername() {
        return user.getPrimaryEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
