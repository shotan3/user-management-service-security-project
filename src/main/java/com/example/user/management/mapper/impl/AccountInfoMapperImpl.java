package com.example.user.management.mapper.impl;

import com.example.user.management.dto.AccountInfoDto;
import com.example.user.management.entity.AccountInfo;
import com.example.user.management.mapper.AccountInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountInfoMapperImpl implements AccountInfoMapper {

    @Override
    public AccountInfoDto mapToAccountInfoDto(AccountInfo accountInfo) {
        return AccountInfoDto.builder()
                .id(accountInfo.getId())
                .createdAt(accountInfo.getCreatedAt())
                .updatedAt(accountInfo.getUpdatedAt())
                .markedForDeletion(accountInfo.isMarkedForDeletion())
                .deletionScheduledAt(accountInfo.getDeletionScheduledAt())
                .userUuid(accountInfo.getUser().getUserUUID())
                .build();
    }


}
