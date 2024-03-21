package com.example.user.management.controller;

import com.example.user.management.dto.AccountInfoDto;
import com.example.user.management.dto.UserDto;
import com.example.user.management.dto.request.UserRegistrationRequest;
import com.example.user.management.exception.custom.ResourceNotFoundException;
import com.example.user.management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRegistrationRequest userRequest) {
        return service.registerUser(userRequest)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatusCode.valueOf(HttpStatus.CREATED.value())))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(value = "/{userUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userUuid) {
        return service.getUserById(userUuid)
                .map(ResponseEntity::ok)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PutMapping(value = "/{userUuid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUserInfo(@PathVariable UUID userUuid, @RequestBody @Valid UserDto userRequest) {
        return service.updateUserInfo(userUuid, userRequest)
                .map(ResponseEntity::ok)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping(value = "/{userUuid}")
    public ResponseEntity<AccountInfoDto> softDeleteUserByUuid(@PathVariable UUID userUuid) {
        return service.softDeleteUserByUuid(userUuid)
                .map(ResponseEntity::ok)
                .orElseThrow(ResourceNotFoundException::new);
    }

}
