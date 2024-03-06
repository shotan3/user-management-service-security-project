package com.example.user.management.mapper;

import com.example.user.management.dto.LoginHistoryDto;
import com.example.user.management.entity.LoginHistory;

public interface LoginHistoryMapper {

    LoginHistoryDto mapToLoginHistoryDto(LoginHistory loginHistory);

}
