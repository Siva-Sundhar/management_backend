package com.cloud9.Management.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String username;

    private String mobileNo;

    @Enumerated(EnumType.STRING)
    private Roles role;

    private String email;

    @ToString.Exclude
    private String password;

}