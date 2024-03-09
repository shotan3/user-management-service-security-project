package com.example.user.management.entity;

import com.example.user.management.dto.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "\"user\"", schema = "\"user\"")
public class User {

    @Id
    @UuidGenerator
    @Column(name = "user_uuid")
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

    @ManyToOne
    @JoinColumn(name = "role_name")
    Role role;

}
