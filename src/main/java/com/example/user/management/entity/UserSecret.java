package com.example.user.management.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSecret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String currPassword;

    @Column
    String prevPassword;

    @Column
    String passwordSalt;

    @Column
    ZonedDateTime passwordLastUpdateTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    User userUUID;
}
