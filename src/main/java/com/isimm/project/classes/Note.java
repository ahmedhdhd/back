package com.isimm.project.classes;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime; // Import the LocalDateTime class

@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String date;
    private String path;
}
