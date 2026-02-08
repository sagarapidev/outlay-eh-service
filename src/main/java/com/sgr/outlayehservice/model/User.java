package com.sgr.outlayehservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Name",nullable = false, length = 100)
    private String name;

    @Column(name ="Email",nullable = false , unique = true, length = 150)
    private String email;

    @Column(name = "CreatedOn", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "UpdatedOn")
    private LocalDateTime updatedOn;
}

