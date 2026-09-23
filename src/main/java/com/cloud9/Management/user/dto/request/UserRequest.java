package com.cloud9.Management.user.dto.request;

public record UserRequest(
        String email,
        String password,
        String mobileNo,
        String username
) {
}
