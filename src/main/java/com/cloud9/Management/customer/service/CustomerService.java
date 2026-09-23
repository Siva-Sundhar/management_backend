package com.cloud9.Management.customer.service;

import com.cloud9.Management.customer.dto.request.CustomerRequest;
import com.cloud9.Management.customer.dto.response.CustomerResponse;
import com.cloud9.Management.customer.entity.Customer;
import com.cloud9.Management.customer.mapper.CustomerMapper;
import com.cloud9.Management.customer.repository.CustomerRepository;
import com.cloud9.Management.executive.repository.ExecutiveRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse saveCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        customerRepository.save(customer);

        return customerMapper.toResponse(customer);
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerMapper.toResponseList(customerRepository.findAll());
    }


}
