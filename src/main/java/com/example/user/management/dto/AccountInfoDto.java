package com.example.user.management.dto;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Data
public class AccountInfoDto {

    long id;

    ZonedDateTime createdAt;

    ZonedDateTime updatedAt;

    boolean markedForDeletion;

    ZonedDateTime deletionScheduledAt;

    UUID userUuid;
}
