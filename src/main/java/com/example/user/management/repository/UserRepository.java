package com.example.user.management.repository;

import com.example.user.management.entity.User;
import com.example.user.management.entity.UserSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.primaryEmail = :username OR u.contactPhone = :username")
    Optional<User> findByEmailOrContactPhone(String username);

    @Query("SELECT s FROM UserSecret s WHERE s.userUUID.userUUID = :userUuid")
    Optional<UserSecret> findUserSecretByUserUuid(UUID userUuid);

}
