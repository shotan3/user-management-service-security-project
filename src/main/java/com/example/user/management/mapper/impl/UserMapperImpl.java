package com.example.user.management.mapper.impl;

import com.example.user.management.dto.UserDto;
import com.example.user.management.entity.User;
import com.example.user.management.mapper.UserMapper;

public class UserMapperImpl implements UserMapper {
    @Override
    public User mapToUserEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
                .userUUID(userDto.getUserUUID())
                .firstName(userDto.getFirstName())
                .middleName(userDto.getMiddleName())
                .lastName(userDto.getLastName())
                .dateOfBirth(userDto.getDateOfBirth())
                .gender(userDto.getGender())
                .primaryEmail(userDto.getPrimaryEmail())
                .contactPhone(userDto.getContactPhone())
                .backupEmail(userDto.getBackupEmail())
                .countryOfResidence(userDto.getCountryOfResidence())
                .cityOfResidence(userDto.getCityOfResidence())
                .role(userDto.getRole())
                .build();

    }

    @Override
    public UserDto mapToUserDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .userUUID(user.getUserUUID())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .primaryEmail(user.getPrimaryEmail())
                .contactPhone(user.getContactPhone())
                .backupEmail(user.getBackupEmail())
                .countryOfResidence(user.getCountryOfResidence())
                .cityOfResidence(user.getCityOfResidence())
                .role(user.getRole())
                .build();
    }
}
