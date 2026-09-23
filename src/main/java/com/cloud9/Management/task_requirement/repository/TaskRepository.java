package com.cloud9.Management.task_requirement.repository;

import com.cloud9.Management.task_requirement.model.TaskRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskRequirement, Long> {
    List<TaskRequirement> findByVoucherDate(LocalDate date);
}
