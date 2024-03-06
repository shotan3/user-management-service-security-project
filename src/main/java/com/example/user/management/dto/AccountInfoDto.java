package com.example.user.management.dto;

import com.example.user.management.entity.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class AccountInfoDto {

    long id;

    @Past
    ZonedDateTime createdAt;

    @Past
    ZonedDateTime updatedAt;

    boolean markedForDeletion;

    @Future
    ZonedDateTime deletionScheduledAt;

    User user;
}
