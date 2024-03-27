package com.example.user.management.dto.request;

import lombok.Data;

@Data
public class PasswordChangeRequest {

    String currPassword;

    String newPassword;

}
