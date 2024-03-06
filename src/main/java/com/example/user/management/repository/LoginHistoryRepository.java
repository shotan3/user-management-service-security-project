package com.example.user.management.repository;

import com.example.user.management.entity.LoginHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Long> {

    @Query("SELECT l FROM LoginHistory l WHERE l.user.userUUID = :userUuid")
    Optional<LoginHistory> findByUserUuid(UUID userUuid);

}
