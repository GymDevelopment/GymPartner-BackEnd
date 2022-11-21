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
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AssignedDietController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientRepository coachRepository;
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
    @Transactional
    @PostMapping("/assignedDiets")
    public ResponseEntity<AssignedDiet> createAssignedDiet(@RequestBody AssignedDiet assignedDiet){
        System.out.println("assignedDiet: " + assignedDiet);
        AssignedDiet newAssignedDiet = assignedDietRepository.save(assignedDiet);
        return new ResponseEntity<>(newAssignedDiet, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/assignedDietsTodayByClientId/{clientId}")
    public ResponseEntity<List<AssignedDiet>> getAssignedDietsTodayByClientId(@PathVariable("clientId") Long clientId){

        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<AssignedDiet> diets = assignedDietRepository.findAssignedDietByClientIdJPQL(clientId);

        return new ResponseEntity<>(diets, HttpStatus.OK);
    }

}
