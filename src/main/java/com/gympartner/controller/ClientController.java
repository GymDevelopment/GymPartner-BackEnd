package com.gympartner.controller;

import com.gympartner.converter.ClientConverter;
import com.gympartner.dto.ClientResponseDTO;
import com.gympartner.dto.ConsultClientDTO;
import com.gympartner.dto.LoginRequestDTO;
import com.gympartner.entities.AssignedRoutine;
import com.gympartner.entities.Client;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.ClientRepository;
import com.gympartner.repository.CoachRepository;
import com.gympartner.util.AssignedRoutineExcelExporter;
import com.gympartner.util.ClientExcelExporter;
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
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CoachRepository coachRepository;

    private final ClientConverter clientConverter;

    public ClientController(ClientConverter clientConverter) {
        this.clientConverter = clientConverter;
    }

    @Transactional
    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client newClient = clientRepository.save(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
    @Transactional(readOnly = true)
    @GetMapping("/coaches/{coachId}/clients")
    public ResponseEntity<List<Client>> getAllClientByCoachId(@PathVariable("coachId") Long coachId){
        if(!coachRepository.existsById(coachId)){
            throw new ResourceNotFoundException("Not found coach with id = " + coachId);
        }
        List<Client> clients = clientRepository.findByCoachId(coachId);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){
        Client newClient = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found client with id = " + id));
        return new ResponseEntity<>(newClient, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients= clientRepository.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/coaches/{coachId}/assignedRoutine/export/excel")
    public void exportToExcel(HttpServletResponse response, @PathVariable("coachId") Long coachId) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_clients";
        response.setHeader(headerKey, headerValue);

        List<Client> clients = clientRepository.findByCoachId(coachId);
        ClientExcelExporter excelExporter = new ClientExcelExporter(clients);
        excelExporter.export(response);
    }
    @Transactional (readOnly = true)
    @GetMapping("/coaches/{coachId}/clients/search/fullName")
    public ResponseEntity<List<Client>> searchByFullName(@PathVariable("coachId") Long coachId ,@RequestParam(value = "name") String name, @RequestParam(value = "lastName") String lastName){
        List<Client> clients = clientRepository.search(coachId, name, lastName);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @PostMapping("/client/signIn")
    public ResponseEntity<ClientResponseDTO> signInClient(@RequestBody LoginRequestDTO request) {
        Client clientSignIn=clientRepository
                .findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(()-> new ResourceNotFoundException("Email y/o password incorrectos"));
        ClientResponseDTO response=clientConverter.convertEntityToDto(clientSignIn);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional (readOnly = true)
    @GetMapping("/client/callProcedure")
    public ResponseEntity<List<ConsultClientDTO>> callProcOrFunction(){
        List<ConsultClientDTO> consults = new ArrayList<>();
        clientRepository.callProcedureOrFunction().forEach(x -> {
            ConsultClientDTO dto = new ConsultClientDTO();
            dto.setPhysicalState((String) x[0]);
            dto.setQuantity((Integer) x[1]);
            dto.setRoutineDuration((Integer) x[2]);
            dto.setAverageAge((Double) x[3]);
            consults.add(dto);
        });
        return new ResponseEntity<>(consults, HttpStatus.OK);
    }

}
