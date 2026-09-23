package com.cloud9.Management.executive.mapper;

import com.cloud9.Management.auth.dto.AuthResponse;
import com.cloud9.Management.executive.dto.request.ExecutiveRequest;
import com.cloud9.Management.executive.dto.response.ExecutiveResponse;
import com.cloud9.Management.executive.entity.Executive;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExecutiveMapper {


    @Mapping(target = "id", ignore = true)
    Executive toEntity(ExecutiveRequest request);

    ExecutiveResponse toResponse(Executive executive);

    List<ExecutiveResponse> toResponseList(List<Executive> executives);

//    default AuthResponse toAuthResponse(Executive executive, String token) {
//        return new AuthResponse(
//                token,
//                executive.getId(),
//                executive.getUsername(),
//                executive.getEmail(),
//                executive.getRole() != null ? executive.getRole().name() : null
//        );
//    }
}
