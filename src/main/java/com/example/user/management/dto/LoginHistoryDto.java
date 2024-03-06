package com.example.user.management.dto;

import com.example.user.management.dto.enums.LoginFailureReason;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Data
public class LoginHistoryDto {

    long id;

    ZonedDateTime loginTime;

    String sessionId;

    boolean wasSuccessful;

    String ipAddress;

    LoginFailureReason failureReason;

    UUID userUuid;

}
