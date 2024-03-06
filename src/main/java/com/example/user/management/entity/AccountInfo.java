package com.example.user.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "user_account_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    ZonedDateTime createdAt;

    @Column
    ZonedDateTime updatedAt;

    @Column
    boolean markedForDeletion;

    @Column
    ZonedDateTime deletionScheduledAt;

    @OneToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    User user;

}
