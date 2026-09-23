package com.cloud9.Management.auth.dto;

public record LoginRequest(
    String username,
    String password
) {
}
