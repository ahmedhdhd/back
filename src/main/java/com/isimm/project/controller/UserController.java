package com.isimm.project.controller;

import com.isimm.project.classes.Enseignant;
import com.isimm.project.classes.Note;
import com.isimm.project.classes.User;
import com.isimm.project.repository.StudentRepository;
import com.isimm.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired

    private UserRepository userRepository;
    @Autowired

    private StudentRepository studentRepository;

    @PostMapping("/save")
public ResponseEntity<User> saveUser(@RequestBody User user){
        User u= userRepository.save(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);}
@GetMapping("/all")
    public List<User> allUser(){
        return userRepository.findAll();}


    @PutMapping("/forgetpass/{username}/{password}")
    public void updatepasswaord(@PathVariable("username") String username , @PathVariable("password") String password){
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isPresent()) {
            User useri  = user.get();
            useri.setPassword(password);
            userRepository.save(useri);
        }
    }
}
