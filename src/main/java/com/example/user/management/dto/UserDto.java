package com.example.user.management.dto;

import com.example.user.management.dto.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDate;

@Builder
public class UserDto {

    @UUID
    private String userUUID;

    @Length(max = 64)
    @NotBlank
    private String firstName;

    @Length(max = 64)
    private String middleName;

    @Length(max = 64)
    @NotBlank
    private String lastName;

    @Past
    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    @Email
    @NotNull
    private String primaryEmail;

    @Length(max = 64)
    private String contactPhone;

    @Email
    private String backupEmail;

    @Length(max = 64)
    private String countryOfResidence;

    @Length(max = 128)
    private String cityOfResidence;

}
