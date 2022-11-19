package com.gympartner.controller;

import com.gympartner.entities.AssignedDiet;
import com.gympartner.entities.AssignedRoutine;
import com.gympartner.entities.Diet;
import com.gympartner.entities.Gym;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.AssignedDietRepository;
import com.gympartner.repository.ClientRepository;
import com.gympartner.util.GymExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AssignedDietController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AssignedDietRepository assignedDietRepository;
    @Transactional(readOnly = true)
    @GetMapping("/clients/{clientId}/assignedDiet")
    public ResponseEntity<List<AssignedDiet>> getAllAssignedDietsByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<AssignedDiet> assignedDiets = assignedDietRepository.findByClientId(clientId);
        return new ResponseEntity<>(assignedDiets, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/clients/{clientId}/breakfast")
    public ResponseEntity<List<Diet>> getAllBreakfastByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<Diet> diets = assignedDietRepository.findAllBreakfastByClientIdJPQL(clientId);
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/clients/{clientId}/lunch")
    public ResponseEntity<List<Diet>> getAllLunchByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<Diet> diets = assignedDietRepository.findAllLunchByClientIdJPQL(clientId);
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/clients/{clientId}/dinner")
    public ResponseEntity<List<Diet>> getAllDinnerByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<Diet> diets = assignedDietRepository.findAllDinnerByClientIdJPQL(clientId);
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/assignedDiets")
    public ResponseEntity<AssignedDiet> createAssignedDiet(@RequestBody AssignedDiet assignedDiet){
        AssignedDiet newAssignedDiet = assignedDietRepository.save(assignedDiet);
        return new ResponseEntity<>(newAssignedDiet, HttpStatus.CREATED);
    }



}
