package com.cloud9.Management.customer.controller;

import com.cloud9.Management.customer.dto.request.CustomerRequest;
import com.cloud9.Management.customer.dto.response.CustomerResponse;
import com.cloud9.Management.customer.entity.Customer;
import com.cloud9.Management.customer.repository.CustomerRepository;
import com.cloud9.Management.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/save")
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerRequest customer) {
        return ResponseEntity.ok(service.saveCustomer(customer));
    }

    @GetMapping("/all-customer")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }
}

