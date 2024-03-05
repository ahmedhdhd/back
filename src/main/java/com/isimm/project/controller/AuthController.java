package com.isimm.project.controller;

import com.isimm.project.classes.User;
import com.isimm.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login/{name}/{pass}")
    public  ResponseEntity<?>  authenticateUser(@PathVariable("name") String name, @PathVariable("pass") String pass) {
        User user = userRepository.findByUsernameAndPassword(name,pass);
        if (user != null) {
            return ResponseEntity.ok(new AuthResponse("Login successful", user.getRole()));
        } else { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid credentials"));
        }
    }
}
