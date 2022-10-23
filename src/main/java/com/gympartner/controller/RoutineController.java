package com.gympartner.controller;

import com.gympartner.entities.Routine;
import com.gympartner.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoutineController {
    @Autowired
    private RoutineRepository routineRepository;
    @PostMapping("/routines")
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine){
        Routine newRoutine = routineRepository.save(routine);
        return new ResponseEntity<>(newRoutine, HttpStatus.CREATED);
    }
    @GetMapping("/routines")
    public ResponseEntity<List<Routine>> getAllRoutines(){
        List<Routine> routines = routineRepository.findAll();
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }
}
