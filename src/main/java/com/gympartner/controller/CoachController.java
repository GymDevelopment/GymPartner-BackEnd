package com.gympartner.controller;

import com.gympartner.entities.Coach;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CoachController {
    @Autowired
    private CoachRepository coachRepository;
    @PostMapping("/coaches")
    public ResponseEntity<Coach> createCoach(@RequestBody Coach coach){
        Coach newCoach = coachRepository.save(coach);
        return new ResponseEntity<>(newCoach, HttpStatus.CREATED);
    }
    @GetMapping("/coaches")
    public ResponseEntity<List<Coach>> getAllCoaches(){
        List<Coach> coaches = coachRepository.findAll();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }
    @GetMapping("/coaches/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable("id") Long id){
        Coach newCoach = coachRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found coach with id = " + id));
        return new ResponseEntity<>(newCoach, HttpStatus.OK);
    }
}
