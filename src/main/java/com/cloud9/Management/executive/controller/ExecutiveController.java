package com.cloud9.Management.executive.controller;

import com.cloud9.Management.executive.dto.request.ExecutiveRequest;
import com.cloud9.Management.executive.dto.response.ExecutiveResponse;
import com.cloud9.Management.executive.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/executive")
public class ExecutiveController {

    private final ExecutiveService executiveService;

    @PostMapping("/save")
    public ResponseEntity<ExecutiveResponse> save(@Valid @RequestBody ExecutiveRequest request) {
        return ResponseEntity.ok(executiveService.saveExecutive(request));
    }

    @GetMapping("/all-executive")
    public ResponseEntity<List<ExecutiveResponse>> getAllExecutive() {
        return ResponseEntity.ok(executiveService.getAllExecutive());
    }
}