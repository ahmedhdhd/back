package com.isimm.project.controller;

import com.isimm.project.classes.Enseignant;
import com.isimm.project.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enseignants")
public class EnseignantController {

    @Autowired
    private EnseignantRepository enseignantService;

    @PostMapping("/add")
    public Enseignant createEnseignant(@RequestBody Enseignant enseignant) {
        return enseignantService.save(enseignant);
    }

    @GetMapping("/all")
    public List<Enseignant> getAllEnseignants() {
        return enseignantService.findAll();
    }


    @PutMapping("/updatematier/{id}")
    public void updatematier(@PathVariable("id") Long id , @RequestBody List<String>  matier){
        Optional<Enseignant> enseignantOptional = enseignantService.findById(id);
        if (enseignantOptional.isPresent()) {
            Enseignant enseignant = enseignantOptional.get();
            enseignant.setMatiereEnseignee(matier);
            enseignantService.save(enseignant);
        }


    }



    @PutMapping("/updateclasses/{id}")
    public void updateclass(@PathVariable("id") Long id , @RequestBody List<String>  classes){
        Optional<Enseignant> enseignantOptional = enseignantService.findById(id);
        if (enseignantOptional.isPresent()) {
            Enseignant enseignant = enseignantOptional.get();
            enseignant.setClassesEnseignee(classes);
            enseignantService.save(enseignant);
        }


    }
}
