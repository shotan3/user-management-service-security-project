package com.example.user.management.service;

import com.example.user.management.dto.AccountInfoDto;
import com.example.user.management.dto.UserDto;
import com.example.user.management.dto.request.UserRegistrationRequest;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<UserDto> registerUser(UserRegistrationRequest userRequest);

    Optional<UserDto> getUserById(UUID userUuid);

    Optional<UserDto> updateUserInfo(UUID userUuid, UserDto userRequest);

    Optional<AccountInfoDto> softDeleteUserByUuid(UUID userUuid);

}
