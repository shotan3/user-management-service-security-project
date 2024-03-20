package com.example.user.management.entity;

import com.example.user.management.dto.enums.LoginFailureReason;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    ZonedDateTime loginTime;

    @Column
    String sessionId;

    @Column
    boolean wasSuccessful;

    @Column
    String ipAddress;

    @Column
    @Enumerated(EnumType.STRING)
    LoginFailureReason failureReason;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    User user;

}
