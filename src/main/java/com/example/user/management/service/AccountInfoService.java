package com.example.user.management.service;

import com.example.user.management.dto.AccountInfoDto;

import java.util.Optional;
import java.util.UUID;

public interface AccountInfoService {

    Optional<AccountInfoDto> getAccountInfoDto(UUID userUuid);

}
