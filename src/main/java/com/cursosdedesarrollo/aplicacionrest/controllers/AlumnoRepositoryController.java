package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.domain.Alumno;
import com.cursosdedesarrollo.aplicacionrest.domain.AlumnoDTO;
import com.cursosdedesarrollo.aplicacionrest.repositories.AlumnoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/alumno-controller")
public class AlumnoRepositoryController {
    @Autowired
    AlumnoRepository alumnoRepository;

    @GetMapping("/")
    public ResponseEntity<List<Alumno>> index(){
        return ResponseEntity.ok(this.alumnoRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Alumno> create(
            @RequestBody @Valid AlumnoDTO alumnoDTO){
        Alumno alumno = new Alumno(alumnoDTO);
        return ResponseEntity.ok(this.alumnoRepository.save(alumno));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> show(@Valid @PathVariable("id") Long id){
        Optional<Alumno> alumno = this.alumnoRepository.findById(id);
        if(alumno.isPresent()){
            return ResponseEntity.ok(alumno.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        // return alumno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> update(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody AlumnoDTO alumnoDTO) {
        Optional<Alumno> alumno = this.alumnoRepository.findById(id);
        if (alumno.isPresent()) {
            Alumno alumnoUpdate = new Alumno(alumnoDTO);
            alumnoUpdate.setId(id);
            return ResponseEntity.ok(this.alumnoRepository.save(alumnoUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Alumno> delete(@Valid @PathVariable("id") Long id){
        Optional<Alumno> alumno = this.alumnoRepository.findById(id);
        if(alumno.isPresent()){
            this.alumnoRepository.deleteById(id);
            return ResponseEntity.ok(alumno.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
