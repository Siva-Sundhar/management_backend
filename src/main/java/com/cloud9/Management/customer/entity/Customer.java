package com.cloud9.Management.customer.entity;

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
public class Customer {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String customerCode;

    private String customerName;
}
