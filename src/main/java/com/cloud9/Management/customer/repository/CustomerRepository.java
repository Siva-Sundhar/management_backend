package com.cloud9.Management.customer.repository;

import com.cloud9.Management.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
