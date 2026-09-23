package com.cloud9.Management.executive.service;

import com.cloud9.Management.executive.dto.request.ExecutiveRequest;
import com.cloud9.Management.executive.dto.response.ExecutiveResponse;
import com.cloud9.Management.executive.entity.Executive;
import com.cloud9.Management.executive.mapper.ExecutiveMapper;
import com.cloud9.Management.executive.repository.ExecutiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecutiveService{

    private final ExecutiveRepository executiveRepository;
    private final ExecutiveMapper executiveMapper;

    public ExecutiveResponse saveExecutive(ExecutiveRequest request){
        Executive executive = executiveMapper.toEntity(request);
        executiveRepository.save(executive);
        return executiveMapper.toResponse(executive);
    }

    public List<ExecutiveResponse> getAllExecutive(){
        return executiveMapper.toResponseList(executiveRepository.findAll());
    }

}