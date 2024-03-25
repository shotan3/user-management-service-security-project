package com.example.user.management.repository;

import com.example.user.management.dto.request.UserFilterRequest;
import com.example.user.management.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> filterUserBy(UserFilterRequest filter);

}
