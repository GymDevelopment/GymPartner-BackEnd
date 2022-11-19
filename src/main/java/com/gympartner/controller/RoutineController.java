package com.gympartner.controller;

import com.gympartner.entities.Routine;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RoutineController {
    @Autowired
    private RoutineRepository routineRepository;
    @Transactional
    @PostMapping("/routines")
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine){
        Routine newRoutine = routineRepository.save(routine);
        return new ResponseEntity<>(newRoutine, HttpStatus.CREATED);
    }
    @Transactional(readOnly = true)
    @GetMapping("/routines")
    public ResponseEntity<List<Routine>> getAllRoutines(){
        List<Routine> routines = routineRepository.findAll();
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/routines/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable("id") Long id){
        Routine routines = routineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found routine with id = " + id));
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/coaches/{coachId}/routines")
    public ResponseEntity<List<Routine>> getAllRoutinesByCoach( @PathVariable("coachId") Long coachId){
        List<Routine> routines = routineRepository.findByCoachId(coachId);
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }
    @Transactional
    @PutMapping("/routines/{id}")
    public ResponseEntity<Routine> updateRoutine(@PathVariable("id") Long id, @RequestBody Routine routine){
        Routine newRoutine = routineRepository.save(routine);
        return new ResponseEntity<>(newRoutine, HttpStatus.OK);
    }
}
