package com.gympartner.controller;

import com.gympartner.entities.Coach;
import com.gympartner.entities.Diet;
import com.gympartner.exception.ResourceNotFoundException;
import com.gympartner.repository.CoachRepository;
import com.gympartner.repository.DietRepository;
import com.gympartner.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DietController {
    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private CoachRepository coachRepository;

/*
    @PostMapping("/diets")
    public ResponseEntity<Diet> createDiet(@RequestBody Diet diet){
        Diet newDiet = dietRepository.save(diet);
        return new ResponseEntity<>(newDiet, HttpStatus.CREATED);
    }
 */
/*
    @GetMapping("/diets")
    public ResponseEntity<List<Diet>> getAllDiets(){
        List<Diet> diets = dietRepository.findAll();
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }

 */
    @PostMapping("/diets")
    @Transactional
    //public ResponseEntity<Product> save(@RequestBody Product product) {
    public ResponseEntity<Diet> save(   @RequestParam("name") String name,
                                        @RequestParam("meal") String meal,
                                        @RequestParam("indication") String indication,
                                        @RequestParam("calories") Integer calories,
                                        @RequestParam("hour") Integer hour,
                                        @RequestParam("mealType") String mealType,
                                        @RequestParam("coachId") Long coachId,
                                        @RequestParam("picture") MultipartFile picture) throws IOException {

        Coach coach = coachRepository.findById(coachId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found coach with id="+coachId));
        Diet diet = new Diet(
                name,
                meal,
                indication,
                calories,
                hour,
                mealType,
                Util.compressZLib(picture.getBytes())
        );
        if(coach!=null) {
            diet.setCoach(coach);
        }
        Diet dietSaved=dietRepository.save(diet);
        return new ResponseEntity<>(dietSaved,HttpStatus.CREATED);
    }

    @GetMapping("/diets")
    @Transactional (readOnly = true)
    public ResponseEntity<List<Diet>> search() {
        List<Diet> diets=new ArrayList<>();
        List<Diet> dietsAux= new ArrayList<>();
        dietsAux=dietRepository.findAll();

        if(dietsAux.size()>0){
            dietsAux.stream().forEach((p)->{
                byte[] imageDescompressed = Util.decompressZLib(p.getPicture());
                p.setPicture(imageDescompressed);
                diets.add(p);
            });
        }
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }

    @GetMapping("/diets/{id}")
    @Transactional (readOnly = true)
    public ResponseEntity<Diet> searchById(@PathVariable("id") Long id){
        Diet diet=dietRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found diet with id="+id));

        if(diet!=null){
            byte[] imageDescompressed = Util.decompressZLib(diet.getPicture());
            diet.setPicture(imageDescompressed);
        }
        System.out.println("diet: "+diet);
        return new ResponseEntity<>(diet,HttpStatus.OK);
    }

}
