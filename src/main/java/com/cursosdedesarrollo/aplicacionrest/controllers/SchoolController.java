package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.domain.School;
import com.cursosdedesarrollo.aplicacionrest.dtos.SchoolCreateUpdateDTO;
import com.cursosdedesarrollo.aplicacionrest.services.SchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/search")
    public ResponseEntity<Page<School>> getAllSchoolsPaginated(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(schoolService.findAllPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getById(
        @PathVariable("id") Long id
    ){
        School school = this.schoolService.findById(id);
        if(school.getId() == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(school);
        }
    }

}
