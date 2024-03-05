package com.isimm.project.classes;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "app_user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private String email;
}


