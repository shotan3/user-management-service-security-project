package com.example.user.management.entity;

import com.example.user.management.dto.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    @Id
    @Column(name = "user_uuid")
    @UuidGenerator()
    UUID userUUID;

    @Column
    String firstName;

    @Column
    String middleName;

    @Column
    String lastName;

    @Column
    LocalDate dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column
    String primaryEmail;

    @Column
    String contactPhone;

    @Column
    String backupEmail;

    @Column
    String countryOfResidence;

    @Column
    String cityOfResidence;

}
