package com.example.user.management.service.security;

import com.example.user.management.dto.UserDto;
import com.example.user.management.dto.UserSecretDto;
import com.example.user.management.entity.User;
import com.example.user.management.entity.UserSecret;
import com.example.user.management.mapper.UserMapper;
import com.example.user.management.mapper.UserSecretMapper;
import com.example.user.management.repository.UserRepositoryExtended;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    private final UserSecretMapper userSecretMapper;

    private final UserRepositoryExtended userRepositoryExtended;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto user = userMapper.mapToUserDto(getUserByUsername(username));
        UserSecretDto userSecret = userSecretMapper.mapToUserSecretDto(getUserSecretByUuid(user.getUserUUID()));

        return new CustomUserDetails(user, userSecret);
    }

    private UserSecret getUserSecretByUuid(UUID userUuid) {
        Optional<UserSecret> userSecret = userRepositoryExtended.findUserSecretByUserUuid(userUuid);
        return userSecret.orElseThrow(() -> {
            String msg = getUserNotFoundErrorMsg(userUuid.toString());
            log.error(msg);
            return new UsernameNotFoundException(msg);
        });
    }

    private User getUserByUsername(String username) {
        Optional<User> user = userRepositoryExtended.findByEmailOrContactPhone(username);
        return user.orElseThrow(() -> {
            String msg = getUserNotFoundErrorMsg(username);
            log.error(msg);
            return new UsernameNotFoundException(msg);
        });
    }


    private String getUserNotFoundErrorMsg(String user) {
        return String.format("User error: %s", user);
    }
}
