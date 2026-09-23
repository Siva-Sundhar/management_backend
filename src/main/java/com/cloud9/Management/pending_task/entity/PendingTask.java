package com.cloud9.Management.pending_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PendingTask{

    @Id
    Long id;

}