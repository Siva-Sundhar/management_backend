package com.cloud9.Management.security;

import com.cloud9.Management.executive.repository.ExecutiveRepository;
import com.cloud9.Management.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository executiveRepository;
    

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return executiveRepository.findByUsername(username)
                .map(executive -> User.withUsername(executive.getUsername())
                        .password(executive.getPassword())
                        .roles(executive.getRole().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Users not found: " + username));
    }
}
