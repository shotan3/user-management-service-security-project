package com.example.user.management.util.validators;

import com.example.user.management.dto.request.PasswordChangeRequest;
import com.example.user.management.entity.UserSecret;
import com.example.user.management.exception.custom.InvalidInputFormatException;
import com.example.user.management.exception.custom.PasswordUpdateNotAllowedException;
import com.example.user.management.exception.custom.UnauthorizedRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public final class PasswordUtilService {

    @Value("${user.config.password.min-length}")
    private int minPassLength;

    @Value("${user.config.password.max-length}")
    private int maxPassLength;

    @Value("${user.config.password.require-uppercase}")
    private boolean upperCaseCharRequired;

    @Value("${user.config.password.require-special}")
    private boolean specialCharRequired;

    @Value("${user.config.password.allowed-special-chars}")
    private String specialChars;

    private final PasswordEncoder encoder;

    public void validatePasswordFormat(String password) {
        Objects.requireNonNull(password);
        boolean hasAppropriateLength = password.length() >= minPassLength && password.length() <= maxPassLength;
        String errorMsg = "Invalid password!";
        if (!hasAppropriateLength) {
            log.error("Password must be between {} and {} characters long", minPassLength, maxPassLength);
            throw new InvalidInputFormatException(errorMsg);
        }

        boolean hasAtLeastOneLetter = password.matches(".*[a-z].*");
        if (!hasAtLeastOneLetter) {
            log.error("Password must have at least one alphabetic character");
            throw new InvalidInputFormatException(errorMsg);
        }

        boolean hasAtLeastOneDigit = password.matches(".*\\d.*");
        if (!hasAtLeastOneDigit) {
            log.error("Password must have at least one digit");
            throw new InvalidInputFormatException(errorMsg);
        }

        boolean upperCaseSatisfied = !upperCaseCharRequired || password.matches(".*[A-Z].*");
        if (!upperCaseSatisfied) {
            log.error("Password must have at least one upper case character");
            throw new InvalidInputFormatException(errorMsg);
        }

        boolean specialCharSatisfied = !specialCharRequired || password.matches(String.format(".*[%s].*", specialChars));
        if (!specialCharSatisfied) {
            log.error("Password must have at least one the special characters: {}", specialChars);
            throw new InvalidInputFormatException(errorMsg);
        }

    }

    public void validatePasswordUpdateRequest(UserSecret secret, PasswordChangeRequest request) {
        Objects.requireNonNull(secret);
        Objects.requireNonNull(request);

        if (request.getCurrPassword().equals(request.getNewPassword())) {
            String reason = "New password must differ from old one!";
            log.error("Failed to update password! Reason: {}", reason);
            throw new PasswordUpdateNotAllowedException(reason);
        }

        if (!encoder.matches(request.getCurrPassword(), secret.getCurrPassword())) {
            String reason = String.format("Incorrect password for user=%s", secret.getUser().getUserUUID());
            log.error("Failed to update password! Reason: {}", reason);
            throw new UnauthorizedRequestException(reason);
        }

        if (encoder.matches(request.getNewPassword(), secret.getPrevPassword())) {
            String reason = "New password must differ from one immediately preceding your current password";
            log.error("Failed to update password! Reason: {}", reason);
            throw new PasswordUpdateNotAllowedException(reason);
        }
    }

    public String encodePassword(String password) {
        return encoder.encode(password);
    }

    public boolean matchesPassword(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }

}
