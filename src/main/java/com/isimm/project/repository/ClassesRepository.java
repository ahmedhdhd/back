package com.isimm.project.repository;


import com.isimm.project.classes.studentClasses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository<studentClasses, Long> {
}
