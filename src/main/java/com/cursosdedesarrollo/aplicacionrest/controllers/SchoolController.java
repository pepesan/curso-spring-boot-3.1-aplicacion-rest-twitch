package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.domain.School;
import com.cursosdedesarrollo.aplicacionrest.dtos.SchoolCreateUpdateDTO;
import com.cursosdedesarrollo.aplicacionrest.services.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/school")
public class SchoolController {

    private SchoolService schoolService;

    @Autowired
    SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @GetMapping("/")
    public ResponseEntity<List<School>> index(){
        return ResponseEntity.ok(this.schoolService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<School> add(
            @Valid @RequestBody SchoolCreateUpdateDTO schoolDTO
    ){
        School createdSchool = this.schoolService.save(schoolDTO);
        return ResponseEntity.ok(createdSchool);
    }
}
