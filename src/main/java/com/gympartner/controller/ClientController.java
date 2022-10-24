package com.gympartner.controller;

import com.gympartner.entities.Client;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.ClientRepository;
import com.gympartner.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CoachRepository coachRepository;
    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client newClient = clientRepository.save(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){
        Client newClient = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found client with id = " + id));
        return new ResponseEntity<>(newClient, HttpStatus.OK);
    }
    @GetMapping("/coaches/{coachId}/clients")
    public ResponseEntity<List<Client>> getAllClientByCoachId(@PathVariable("coachId") Long coachId){
       if(!coachRepository.existsById(coachId)){
            throw new ResourceNotFoundException("Not found coach with id = " + coachId);
       }
        List<Client> clients = clientRepository.findAllClientByCoachIdJPQL(coachId);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients= clientRepository.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
