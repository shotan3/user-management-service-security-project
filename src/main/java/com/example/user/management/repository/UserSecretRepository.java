package com.example.user.management.repository;

import com.example.user.management.entity.UserSecret;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserSecretRepository extends CrudRepository<UserSecret, Long> {


    @Query("SELECT s FROM UserSecret s WHERE s.user.userUUID = :uuid")
    Optional<UserSecret> findByUserUuid(UUID uuid);

}
