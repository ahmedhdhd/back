package com.isimm.project.controller;

import com.isimm.project.classes.studentClasses;
import com.isimm.project.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {
    @Autowired
private ClassesRepository classesRepository;
    @PostMapping("/save")
    public studentClasses saveclasses(@RequestBody studentClasses studentclasses){
        return classesRepository.save(studentclasses);
    }
    @GetMapping("/getclaases")
    public List<studentClasses> getclasses(){
        return classesRepository.findAll();
    }
}

