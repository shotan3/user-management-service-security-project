package com.example.user.management.service.impl;

import com.example.user.management.dto.AccountInfoDto;
import com.example.user.management.mapper.AccountInfoMapper;
import com.example.user.management.repository.AccountInfoRepository;
import com.example.user.management.service.AccountInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountInfoServiceImpl implements AccountInfoService {

    private final AccountInfoRepository repository;
    private final AccountInfoMapper mapper;

    @Override
    public Optional<AccountInfoDto> getAccountInfoDto(UUID userUuid) {
        return repository.findByUserUuid(userUuid)
                .map(mapper::mapToAccountInfoDto);
    }

}
