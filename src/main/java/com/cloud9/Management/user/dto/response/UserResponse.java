package com.cloud9.Management.user.dto.response;

public record UserResponse(
        Long id,
        String email,
        String password,
        String mobileNo,
        String username
) {
}
