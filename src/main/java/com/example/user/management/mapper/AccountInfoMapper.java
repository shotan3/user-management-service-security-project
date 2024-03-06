package com.example.user.management.mapper;

import com.example.user.management.dto.AccountInfoDto;
import com.example.user.management.entity.AccountInfo;


public interface AccountInfoMapper {

    AccountInfoDto mapToAccountInfoDto(AccountInfo accountInfo);

}
