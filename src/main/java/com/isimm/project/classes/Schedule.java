package com.isimm.project.classes;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "date_schedule")
    private String dateSchedule;

    @Column(name = "image_schedule", columnDefinition = "bytea")
    private byte[] imageSchedule;

}
