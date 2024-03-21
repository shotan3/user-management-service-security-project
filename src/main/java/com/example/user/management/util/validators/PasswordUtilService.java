package com.example.user.management.util.validators;

import com.example.user.management.exception.custom.InvalidInputFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public final class PasswordUtilService {

    private final PasswordEncoder encoder;

    @Value("${user.config.password.min-length}")
    private int minPassLength;

    @Value("${user.config.password.max-length}")
    private int maxPassLength;

    @Value("${user.config.password.require-uppercase}")
    private static boolean upperCaseCharRequired;

    @Value("${user.config.password.require-special}")
    private static boolean specialCharRequired;

    @Value("${user.config.password.allowed-special-chars}")
    private static String specialChars;


    public void validatePassword(String password) {
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

    public String encodePassword(String password) {
        return encoder.encode(password);
    }

    public boolean matchesPassword(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }

}
