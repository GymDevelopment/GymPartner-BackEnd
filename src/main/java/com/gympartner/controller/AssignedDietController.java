package com.gympartner.controller;

import com.gympartner.entities.AssignedDiet;
import com.gympartner.entities.Diet;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.AssignedDietRepository;
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
public class AssignedDietController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AssignedDietRepository assignedDietRepository;

    @GetMapping("/clients/{clientId}/assignedDiet")
    public ResponseEntity<List<AssignedDiet>> getAllAssignedDietsByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<AssignedDiet> assignedDiets = assignedDietRepository.findByClientId(clientId);
        return new ResponseEntity<>(assignedDiets, HttpStatus.OK);
    }
    @GetMapping("/clients/{clientId}/diet")
    public ResponseEntity<List<Diet>> getAllDietsByClientId(@PathVariable("clientId") Long clientId){
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("No found client with id = " + clientId);
        }
        List<Diet> diets = assignedDietRepository.findAllDietsByClientIdJPQL(clientId);
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }
}
