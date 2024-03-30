package com.example.user.management.dto.request;

import lombok.Data;

@Data
public class LogoutRequest {

    String authToken;

    String refreshToken;

}
