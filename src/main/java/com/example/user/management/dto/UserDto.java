package com.example.user.management.dto;

import com.example.user.management.dto.enums.Gender;
import com.example.user.management.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class UserDto {

    private UUID userUUID;

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

    private Role role;

}
