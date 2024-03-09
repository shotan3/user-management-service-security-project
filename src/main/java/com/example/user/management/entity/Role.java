package com.example.user.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
public class Role {

    @Id
    @Column
    String roleName;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_to_permission",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "permission_name")
    )
    Set<Authority> authorities;

}
