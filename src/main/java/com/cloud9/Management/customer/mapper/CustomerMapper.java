package com.cloud9.Management.customer.mapper;

import com.cloud9.Management.customer.dto.request.CustomerRequest;
import com.cloud9.Management.customer.dto.response.CustomerResponse;
import com.cloud9.Management.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerRequest request);

    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponseList(List<Customer> customers);

}
