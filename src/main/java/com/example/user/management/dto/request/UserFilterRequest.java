package com.example.user.management.dto.request;

import com.example.user.management.dto.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserFilterRequest {

    private LocalDate fromDateOfBirth;

    private LocalDate toDateOfBirth;

    private Gender gender;

    private String countryOfResidence;

    private String cityOfResidence;

}
