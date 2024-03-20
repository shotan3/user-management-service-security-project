package com.example.user.management.repository;

import com.example.user.management.dto.enums.RoleEnum;
import com.example.user.management.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, String> {

    @Query("SELECT r FROM Role r where r.role = :role")
    Optional<Role> findRoleByRoleName(RoleEnum role);

}
