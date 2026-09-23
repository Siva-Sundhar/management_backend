package com.cloud9.Management.executive.repository;
import com.cloud9.Management.executive.entity.Executive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExecutiveRepository extends JpaRepository<Executive, Long> {

}