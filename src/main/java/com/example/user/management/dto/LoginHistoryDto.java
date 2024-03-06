package com.example.user.management.dto;

import com.example.user.management.dto.enums.LoginFailureReason;
import com.example.user.management.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.ZonedDateTime;

@Builder
@Data
public class LoginHistoryDto {

    long id;

    @Past
    @NotNull
    ZonedDateTime loginTime;

    @Length(max = 64)
    @NotBlank
    String sessionId;

    boolean wasSuccessful;

    @Length(max = 64)
    @NotNull
    String ipAddress;

    LoginFailureReason failureReason;

    User user;

}
