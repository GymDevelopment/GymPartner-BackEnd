package com.gympartner.controller;

import com.gympartner.entities.Gym;
import com.gympartner.repository.GymRepository;
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
public class GymController {
    @Autowired
    private GymRepository gymRepository;

    @GetMapping("/gyms")
    public ResponseEntity<List<Gym>> getAllGyms(){
        List<Gym> gyms = gymRepository.findAll();
        return new ResponseEntity<>(gyms, HttpStatus.OK);
    }

    @PostMapping("/gyms")
    public ResponseEntity<Gym> createGym(@RequestBody Gym gym){
        Gym newGym = gymRepository.save(gym);
        return new ResponseEntity<>(newGym, HttpStatus.CREATED);
    }

    @Transactional(readOnly = true)
    @GetMapping("/gym/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=result_gym";
        response.setHeader(headerKey, headerValue);

        List<Gym> gymResponse = gymRepository.findAll();

        GymExcelExporter excelExporter = new GymExcelExporter(gymResponse);

        excelExporter.export(response);
    }

}
