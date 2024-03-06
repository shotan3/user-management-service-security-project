package com.example.user.management.repository;

import com.example.user.management.entity.AccountInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountInfoRepository extends CrudRepository<AccountInfo, Long> {

    @Query("SELECT a FROM AccountInfo a WHERE a.user.userUUID = :uuid")
    Optional<AccountInfo> findByUserUuid(UUID uuid);

}
