package com.example.user.management.service;

import com.example.user.management.dto.LoginHistoryDto;

import java.util.Optional;
import java.util.UUID;

public interface LoginHistoryService {

    Optional<LoginHistoryDto> getLoginHistory(UUID userUuid);

}
