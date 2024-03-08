package com.example.user.management.mapper;

import com.example.user.management.dto.UserDto;
import com.example.user.management.entity.User;

public interface UserMapper {

    User mapToUserEntity(UserDto userDto);

    UserDto mapToUserDto(User userDto);


}
