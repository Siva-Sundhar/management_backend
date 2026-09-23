package com.cloud9.Management.executive.entity;


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
public class Executive {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "executive_id_sequence"
    )
    @SequenceGenerator(
            name = "executive_id_sequence",
            sequenceName = "executive_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String executiveCode;
    private String executiveName;
}