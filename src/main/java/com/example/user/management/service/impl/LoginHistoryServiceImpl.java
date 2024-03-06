package com.example.user.management.service.impl;

import com.example.user.management.dto.LoginHistoryDto;
import com.example.user.management.mapper.LoginHistoryMapper;
import com.example.user.management.repository.LoginHistoryRepository;
import com.example.user.management.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryMapper mapper;
    private final LoginHistoryRepository repository;

    @Override
    public Optional<LoginHistoryDto> getLoginHistory(UUID userUuid) {
        return repository.findByUserUuid(userUuid)
                .map(mapper::mapToLoginHistoryDto);
    }

}
