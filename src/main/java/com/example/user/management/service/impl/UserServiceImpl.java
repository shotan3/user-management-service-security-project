package com.example.user.management.service.impl;

import com.example.user.management.dto.AccountInfoDto;
import com.example.user.management.dto.RoleDto;
import com.example.user.management.dto.UserDto;
import com.example.user.management.dto.enums.RoleEnum;
import com.example.user.management.dto.request.UserFilterRequest;
import com.example.user.management.dto.request.UserRegistrationRequest;
import com.example.user.management.entity.AccountInfo;
import com.example.user.management.entity.User;
import com.example.user.management.entity.UserSecret;
import com.example.user.management.exception.custom.ResourceNotFoundException;
import com.example.user.management.mapper.AccountInfoMapper;
import com.example.user.management.mapper.RoleMapper;
import com.example.user.management.mapper.UserMapper;
import com.example.user.management.repository.AccountInfoRepository;
import com.example.user.management.repository.RoleRepository;
import com.example.user.management.repository.UserRepositoryExtended;
import com.example.user.management.repository.UserSecretRepository;
import com.example.user.management.service.UserService;
import com.example.user.management.util.validators.PasswordUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.user.management.util.validators.UserRegistrationRequestValidator.validatePhoneNumber;
import static com.example.user.management.util.validators.UserRegistrationRequestValidator.validateUserUniqueness;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryExtended userRepositoryExtended;
    private final RoleRepository roleRepository;
    private final UserSecretRepository userSecretRepository;
    private final AccountInfoRepository accountInfoRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final AccountInfoMapper accountInfoMapper;
    private final PasswordUtilService passwordUtilService;

    @Value("${user.config.account.retention-months.idle}")
    private int defaultRetentionPeriod;

    @Value("${user.config.account.retention-months.marked-for-removal}")
    private int softDeleteRetentionPeriod;


    @Override
    public List<UserDto> filterUsersBy(UserFilterRequest filter) {
        return userRepositoryExtended.filterUserBy(filter).stream()
                .map(userMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(UUID userUuid) {
        return userRepositoryExtended.findById(userUuid).map(userMapper::mapToUserDto);
    }

    @Override
    @Transactional
    public Optional<AccountInfoDto> softDeleteUserByUuid(UUID userUuid) {
        return accountInfoRepository.findByUserUuid(userUuid)
                .flatMap(accountInfo -> markAccountForDeletion(accountInfo)
                        .map(accountInfoMapper::mapToAccountInfoDto));
    }

    private Optional<AccountInfo> markAccountForDeletion(AccountInfo account) {
        account.setMarkedForDeletion(true);
        ZonedDateTime currentTime = ZonedDateTime.now().truncatedTo(ChronoUnit.HOURS);
        account.setDeletionScheduledAt(currentTime.plusMonths(softDeleteRetentionPeriod));
        return Optional.of(accountInfoRepository.save(account));
    }

    @Override
    public Optional<UserDto> updateUserInfo(UUID userUuid, UserDto userRequest) {
        return Optional.of(
                userMapper.mapToUserDto(
                        userRepositoryExtended.save(
                                userRepositoryExtended.findById(userUuid).orElseThrow(ResourceNotFoundException::new))));

    }

    @Transactional
    @Override
    public Optional<UserDto> registerUser(UserRegistrationRequest userRequest) {
        validateUserRegistrationRequest(userRequest);
        encodeUserPassword(userRequest);

        User user = storeUserData(userRequest);
        storeUserPassword(user, userRequest.getPassword());
        storeUserAccountInfo(user);
        return Optional.ofNullable(userMapper.mapToUserDto(user));
    }

    private void validateUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        passwordUtilService.validatePassword(userRegistrationRequest.getPassword());
        validatePhoneNumber(userRegistrationRequest.getUser().getContactPhone());
        validateUserUniqueness(userRepositoryExtended, userRegistrationRequest);
    }

    private void encodeUserPassword(UserRegistrationRequest userRegistrationRequest) {
        String encodedPassword = passwordUtilService.encodePassword(userRegistrationRequest.getPassword());
        userRegistrationRequest.setPassword(encodedPassword);
    }

    private User storeUserData(UserRegistrationRequest userRegistrationRequest) {
        UserDto userDto = userRegistrationRequest.getUser();
        userDto.setUserUUID(null);
        RoleDto role = getDefaultRole();
        userDto.setRole(role);

        return userRepositoryExtended.save(userMapper.mapToUserEntity(userDto));
    }

    private RoleDto getDefaultRole() {
        return roleRepository.findRoleByRoleName(RoleEnum.USER).map(roleMapper::mapToRoleDto).orElseThrow(() -> {
            log.error("Role with name {} not found!", RoleEnum.USER.getRoleName());
            return new RuntimeException();
        });
    }

    private void storeUserPassword(User user, String password) {
        UserSecret userSecret = UserSecret.builder()
                .userUUID(user)
                .currPassword(password)
                .prevPassword(password)
                .passwordLastUpdateTime(ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .build();
        userSecretRepository.save(userSecret);
    }

    private void storeUserAccountInfo(User user) {
        ZonedDateTime now = ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        AccountInfo accountInfo = AccountInfo.builder()
                .markedForDeletion(false)
                .deletionScheduledAt(now.plusMonths(defaultRetentionPeriod))
                .createdAt(now)
                .updatedAt(now)
                .user(user)
                .build();
        accountInfoRepository.save(accountInfo);
    }

}
