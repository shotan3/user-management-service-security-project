package com.example.user.management.util.validators;

import com.example.user.management.dto.request.UserRegistrationRequest;
import com.example.user.management.entity.User;
import com.example.user.management.exception.custom.DuplicateResourceConflictException;
import com.example.user.management.exception.custom.InvalidInputFormatException;
import com.example.user.management.repository.UserRepositoryExtended;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public final class UserRegistrationRequestValidator {

    private UserRegistrationRequestValidator() {
    }


    public static void validateUserUniqueness(UserRepositoryExtended repository, UserRegistrationRequest userRegistrationRequest) {
        String email = userRegistrationRequest.getUser().getPrimaryEmail();
        Optional<User> userByEmail = repository.findByEmail(email);
        String errorMsg = "Resource already exists!";
        if (userByEmail.isPresent()) {
            log.error("Email already registered: {}", email);
            throw new DuplicateResourceConflictException(errorMsg);
        }

        String contactPhone = userRegistrationRequest.getUser().getContactPhone();
        Optional<User> userByPhone = repository.findByContactPhone(contactPhone);
        if (userByPhone.isPresent()) {
            log.error("Phone number already registered: {}", contactPhone);
            throw new DuplicateResourceConflictException(errorMsg);
        }

    }

    public static void validatePhoneNumber(String phoneNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        String errorMsg = String.format("Invalid number: {%s}", phoneNumber);
        try {
            Phonenumber.PhoneNumber parsedNumber = phoneNumberUtil.parse(phoneNumber, null);
            if (!phoneNumberUtil.isValidNumber(parsedNumber)) {
                throw new InvalidInputFormatException(errorMsg);
            }
        } catch (Exception e) {
            log.error(errorMsg);
            throw new InvalidInputFormatException(errorMsg);
        }
    }

}
