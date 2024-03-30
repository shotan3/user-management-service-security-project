package com.example.user.management.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Data
public class LoginHistoryDto {

    long id;

    ZonedDateTime loginTime;

    int loginStatusCode;

    String ipAddress;

    UUID userUuid;

}
