package com.gympartner.controller;

import com.gympartner.converter.CoachConverter;
import com.gympartner.dto.ClientResponseDTO;
import com.gympartner.dto.CoachResponseDTO;
import com.gympartner.dto.LoginRequestDTO;
import com.gympartner.entities.Client;
import com.gympartner.entities.Coach;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CoachController {
    @Autowired
    private CoachRepository coachRepository;

    private final CoachConverter coachConverter;
    public CoachController(CoachConverter coachConverter) {
        this.coachConverter = coachConverter;
    }
    @Transactional
    @PostMapping("/coaches")
    public ResponseEntity<Coach> createCoach(@RequestBody Coach coach){
        Coach newCoach = coachRepository.save(coach);
        return new ResponseEntity<>(newCoach, HttpStatus.CREATED);
    }
    @Transactional(readOnly = true)
    @GetMapping("/coaches")
    public ResponseEntity<List<Coach>> getAllCoaches(){
        List<Coach> coaches = coachRepository.findAll();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/coaches/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable("id") Long id){
        Coach newCoach = coachRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found coach with id = " + id));
        return new ResponseEntity<>(newCoach, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @PostMapping("/coach/signIn")
    public ResponseEntity<CoachResponseDTO> signInCoach(@RequestBody LoginRequestDTO request) {
        Coach coachSignIn=coachRepository
                .findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(()-> new ResourceNotFoundException("Email y/o password incorrectos"));

        CoachResponseDTO response=coachConverter.convertEntityToDto(coachSignIn);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
