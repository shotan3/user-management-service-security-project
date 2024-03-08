package com.example.user.management.mapper;

import com.example.user.management.dto.UserSecretDto;
import com.example.user.management.entity.UserSecret;

public interface UserSecretMapper {

    UserSecretDto mapToUserSecretDto(UserSecret userSecret);
}
