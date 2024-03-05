package com.isimm.project.classes;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Actualite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String continu;
    private Date date;

    @Column(name = "imageAct", columnDefinition = "bytea")
    private byte[] imageAct;


}
