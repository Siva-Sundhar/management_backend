package com.cloud9.Management.user.mapper;

import com.cloud9.Management.auth.dto.AuthResponse;
import com.cloud9.Management.user.dto.request.UserRequest;
import com.cloud9.Management.user.dto.response.UserResponse;
import com.cloud9.Management.user.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Users toEntity (UserRequest userRequest);

    UserResponse toResponse(Users users);

//    default AuthResponse toAuthResponse(Users user, String token) {
//        return new AuthResponse(
//                token,
//                user.getId(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getRole() != null ? user.getRole().name() : null
//        );
//    }
}
