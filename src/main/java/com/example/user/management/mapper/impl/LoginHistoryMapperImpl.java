package com.example.user.management.mapper.impl;

import com.example.user.management.dto.LoginHistoryDto;
import com.example.user.management.entity.LoginHistory;
import com.example.user.management.mapper.LoginHistoryMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginHistoryMapperImpl implements LoginHistoryMapper {

    @Override
    public LoginHistoryDto mapToLoginHistoryDto(LoginHistory loginHistory) {
        if (loginHistory == null) return null;
        return LoginHistoryDto.builder()
                .id(loginHistory.getId())
                .loginTime(loginHistory.getLoginTime())
                .loginStatusCode(loginHistory.getLoginStatusCode())
                .userUuid(loginHistory.getUser().getUserUUID())
                .ipAddress(loginHistory.getIpAddress())
                .build();
    }

}
