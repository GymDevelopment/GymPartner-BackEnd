package com.gympartner.controller;

import com.gympartner.entities.Gym;
import com.gympartner.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GymController {
    @Autowired
    private GymRepository gymRepository;

    @GetMapping("/gyms")
    public ResponseEntity<List<Gym>> getAllGyms(){
        List<Gym> gyms = gymRepository.findAll();
        return new ResponseEntity<>(gyms, HttpStatus.OK);
    }

    @PostMapping("/gyms")
    public ResponseEntity<Gym> createGym(@RequestBody Gym gym){
        Gym newGym = gymRepository.save(gym);
        return new ResponseEntity<>(newGym, HttpStatus.CREATED);
    }
}
