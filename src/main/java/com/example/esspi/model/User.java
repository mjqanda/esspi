package com.example.esspi.model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull // Enforce database integrity constraints
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

}
