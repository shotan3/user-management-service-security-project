package com.example.user.management.dto.request;

import com.example.user.management.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    @Valid
    @JsonUnwrapped
    private UserDto user;

    private String password;

}
