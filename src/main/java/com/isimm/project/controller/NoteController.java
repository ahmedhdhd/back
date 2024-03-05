package com.isimm.project.controller;

import com.isimm.project.classes.Note;
import com.isimm.project.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @PostMapping("/save")
    public ResponseEntity<Note> createNote(@RequestParam("name") String name,
                                           @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date,
                                           @RequestParam("doc") MultipartFile doc) {
        try {
            Note note = new Note();
            note.setName(name);
            note.setDate(date);

            String fileName = StringUtils.cleanPath(doc.getOriginalFilename());
            String uploadDir = "uploads/";
            Path filePath = Paths.get(uploadDir).resolve(fileName).toAbsolutePath();
            Files.copy(doc.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            note.setPath(filePath.toString());

            Note createdNote = noteRepository.save(note);
            return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<List<Note>> getNote(@PathVariable("name") String name){
        List<Note> notes = noteRepository.findByName(name);

        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
}