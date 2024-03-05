package com.isimm.project.classes;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class studentClasses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String classname;
/*
    @OneToMany(mappedBy = "studentClasses")
    private List<Student> students;*/
    @OneToOne
    private Schedule schedule;
}
