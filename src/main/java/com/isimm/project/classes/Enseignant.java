package com.isimm.project.classes;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private int age;
    @Column(name = "matiereEnseignee")
    private List<String> matiereEnseignee;
    private String email;
    private List<String> classesEnseignee ;



}
