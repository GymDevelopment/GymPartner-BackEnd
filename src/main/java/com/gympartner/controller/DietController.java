package com.gympartner.controller;

import com.gympartner.entities.Diet;
import com.gympartner.repository.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DietController {
    @Autowired
    private DietRepository dietRepository;


    @PostMapping("/diets")
    public ResponseEntity<Diet> createDiet(@RequestBody Diet diet){
        Diet newDiet = dietRepository.save(diet);
        return new ResponseEntity<>(newDiet, HttpStatus.CREATED);
    }
    @GetMapping("/diets")
    public ResponseEntity<List<Diet>> getAllDiets(){
        List<Diet> diets = dietRepository.findAll();
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }
}
