package com.cloud9.Management.auth.dto;

public record AuthResponse(

        String accessToken,
        Long executiveId,
        String username,
        String email,
        String role
) {
}
