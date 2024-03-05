package com.example.user.management.dto;

import com.example.user.management.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.time.ZonedDateTime;

@Builder
public class UserSecretDto {

    long id;

    @Length(max = 512)
    @NotBlank
    private String currPassword;

    @Length(max = 512)
    private String prevPassword;

    @Length(max = 32)
    @NotBlank
    private String passwordSalt;

    @Past
    @NotNull
    private ZonedDateTime passwordLastUpdateTime;

    private User user;
}
