package com.example.user.management.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
public class UserSecretDto {

    long id;

    String currPassword;

    String prevPassword;

    ZonedDateTime passwordLastUpdateTime;

    UUID userUUID;

}
