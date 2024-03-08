package com.example.user.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "permission")
@Getter
public class Authority {

    @Id
    @Column(name = "permission_name")
    String authority;

}
