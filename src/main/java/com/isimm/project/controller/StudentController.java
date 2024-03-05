package com.isimm.project.controller;

import com.isimm.project.classes.Student;
import com.isimm.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/{username}")
    public ResponseEntity<Student> getStudentByUsername(@PathVariable String username) {
        Student student = studentRepository.findByUsername(username);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }
    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        if (studentRepository.findByUsername(student.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }
}
