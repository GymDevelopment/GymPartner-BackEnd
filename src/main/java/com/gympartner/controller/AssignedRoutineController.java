package com.gympartner.controller;

import com.gympartner.entities.AssignedRoutine;
import com.gympartner.entities.Routine;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.AssignedRoutineRepository;
import com.gympartner.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssignedRoutineController {
    @Autowired
    private AssignedRoutineRepository assignedRoutineRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients/{clientId}/assignedRoutine")
    public ResponseEntity<List<AssignedRoutine>> getAllAssignedRoutinesByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<AssignedRoutine> assignedRoutines = assignedRoutineRepository.findByClientId(clientId);
        return new ResponseEntity<>(assignedRoutines, HttpStatus.OK);
    }

    @GetMapping("/clients/{clientId}/routine")
    public ResponseEntity<List<Routine>> getAllRoutinesByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<Routine> routines = assignedRoutineRepository.findAllRoutinesByClientIdJPQL(clientId);
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }
}
