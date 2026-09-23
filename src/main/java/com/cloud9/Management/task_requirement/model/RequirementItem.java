package com.cloud9.Management.task_requirement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequirementItem {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_item_id_generator"
    )
    @SequenceGenerator(
            name = "task_item_id_generator",
            sequenceName = "task_item_id_seq",
            allocationSize = 1
    )
    private Long id;
    private String executiveName;
    private String requirement;
    private String analysis;
    private String codingDevelopment;
    private String testing;
    private String demo;
    private String internalChecking;
    private String customerApproval;
    private String deployment;
    private String enhancement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name ="task_requirement_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_task_requirement_task_item")
    )
    private TaskRequirement taskRequirement;
}
