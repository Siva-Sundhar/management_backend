package com.cloud9.Management.pending_task.repository;

import com.cloud9.Management.pending_task.entity.PendingTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingTaskRepository extends JpaRepository<PendingTask, Long> {
}