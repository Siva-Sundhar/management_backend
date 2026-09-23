package com.cloud9.Management.task_requirement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequirement {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_id_generator"
    )
    @SequenceGenerator(
            name = "task_id_generator",
            sequenceName = "task_id_seq",
            allocationSize = 1
    )
    private Long id;
    private String voucherNo;
    private String customerName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate voucherDate;

    private String finalStatus;

    @OneToMany(
            mappedBy = "taskRequirement",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RequirementItem> requirementItems = new ArrayList<>();

    public void addTaskItem(RequirementItem item) {
        requirementItems.add(item);
        item.setTaskRequirement(this);
    }

    public void removeTaskItem(RequirementItem requirementItem) {
        requirementItems.remove(requirementItem);
        requirementItem.setRequirement(null);
    }

}
