package com.example.user.management.mapper.impl;

import com.example.user.management.dto.UserSecretDto;
import com.example.user.management.entity.UserSecret;
import com.example.user.management.mapper.UserSecretMapper;
import org.springframework.stereotype.Service;

@Service
public class UserSecretMapperImpl implements UserSecretMapper {

    @Override
    public UserSecretDto mapToUserSecretDto(UserSecret userSecret) {
        if (userSecret == null) return null;
        return UserSecretDto.builder()
                .id(userSecret.getId())
                .currPassword(userSecret.getCurrPassword())
                .prevPassword(userSecret.getPrevPassword())
                .passwordSalt(userSecret.getPasswordSalt())
                .passwordLastUpdateTime(userSecret.getPasswordLastUpdateTime())
                .userUUID(userSecret.getUserUUID().getUserUUID())
                .build();
    }

}
