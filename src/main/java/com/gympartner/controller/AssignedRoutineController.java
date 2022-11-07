package com.gympartner.controller;

import com.gympartner.entities.AssignedRoutine;
import com.gympartner.entities.Client;
import com.gympartner.entities.Routine;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.AssignedRoutineRepository;
import com.gympartner.repository.ClientRepository;
import com.gympartner.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AssignedRoutineController {
    @Autowired
    private AssignedRoutineRepository assignedRoutineRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoutineRepository routineRepository;

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
    @GetMapping("/routines/{routineId}/client")
    public ResponseEntity<List<Client>> getAllClientsByRoutineId(@PathVariable("routineId") Long routineId){
        if(!routineRepository.existsById(routineId)){
            throw new ResourceNotFoundException("No found routine with id = " + routineId);
        }
        List<Client> clients = assignedRoutineRepository.findAllClientsByRoutineIdJPQL(routineId);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @GetMapping("/assignedRoutines/{id}")
    public ResponseEntity<AssignedRoutine> getAssignedRoutineById(@PathVariable("id") Long id){
        AssignedRoutine assignedRoutine = assignedRoutineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No found assignedRoutine with id = " + id));
        return new ResponseEntity<>(assignedRoutine, HttpStatus.OK);
    }

    @PostMapping("/assignedRoutines")
    public ResponseEntity<AssignedRoutine> createAssignedRoutine(@RequestBody AssignedRoutine assignedRoutine){
        AssignedRoutine newAssignedRoutine = assignedRoutineRepository.save(assignedRoutine);
        return new ResponseEntity<>(newAssignedRoutine, HttpStatus.CREATED);
    }
    @PutMapping("/assignedRoutines/{id}")
    public ResponseEntity<AssignedRoutine> updateAssignedRoutine(@PathVariable("id") Long id, @RequestBody AssignedRoutine assignedRoutine){
        AssignedRoutine newAssignedRoutine = assignedRoutineRepository.save(assignedRoutine);
        return new ResponseEntity<>(newAssignedRoutine, HttpStatus.OK);
    }
    @GetMapping("/clients/{clientId}/routineToday")
    public ResponseEntity<List<Routine>> getAllRoutinesByClientIdToday(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        //List<Routine> routines = assignedRoutineRepository.findAllRoutinesByClientIdJPQLToday(clientId, LocalDate.now());
        List<Routine> routines = assignedRoutineRepository.findAllRoutinesByClientIdJPQLToday(clientId);
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }
    @GetMapping("/clients/{clientId}/todayAssignedRoutines")
    public ResponseEntity<List<AssignedRoutine>> getAllTodayAssignedRoutinesByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<AssignedRoutine> assignedRoutines = assignedRoutineRepository.findAllTodayAssignedRoutinesByClientIdJPQL(clientId);
        return new ResponseEntity<>(assignedRoutines, HttpStatus.OK);
    }
    @GetMapping("/clients/{clientId}/futureAssignedRoutines")
    public ResponseEntity<List<AssignedRoutine>> getAllFutureAssignedRoutinesByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<AssignedRoutine> assignedRoutines = assignedRoutineRepository.findAllFutureAssignedRoutinesByClientIdJPQL(clientId);
        return new ResponseEntity<>(assignedRoutines, HttpStatus.OK);
    }
}
