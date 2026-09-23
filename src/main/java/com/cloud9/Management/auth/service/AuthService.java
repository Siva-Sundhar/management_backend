package com.cloud9.Management.auth.service;

import com.cloud9.Management.auth.dto.AuthResponse;
import com.cloud9.Management.auth.dto.LoginRequest;
import com.cloud9.Management.security.JwtUtils;
import com.cloud9.Management.executive.dto.request.ExecutiveRequest;
import com.cloud9.Management.executive.dto.response.ExecutiveResponse;
import com.cloud9.Management.executive.entity.Executive;
import com.cloud9.Management.executive.mapper.ExecutiveMapper;
import com.cloud9.Management.executive.repository.ExecutiveRepository;
import com.cloud9.Management.user.dto.request.UserRequest;
import com.cloud9.Management.user.dto.response.UserResponse;
import com.cloud9.Management.user.entity.Roles;
import com.cloud9.Management.user.entity.Users;
import com.cloud9.Management.user.mapper.UserMapper;
import com.cloud9.Management.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager manager;
    private final JwtUtils jwtUtils;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;



    @Transactional
    public UserResponse registerUser(UserRequest request) {
        if (repository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already exists!.");
        }

        Users user = mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Roles.ADMIN);

        repository.save(user);

        return mapper.toResponse(user);
    }

    public String authenticateUser(LoginRequest loginRequest) {
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                    )
            );
        } catch(BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
        Users user = repository.findByUsername(loginRequest.username())
                .orElseThrow();

        return jwtUtils.generateToken(
                user.getUsername(),
                user.getId(),
                user.getRole() != null ? user.getRole().name() : "USER"
        );
    }
}
