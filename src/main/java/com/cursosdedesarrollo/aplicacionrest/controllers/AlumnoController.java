package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.domain.Alumno;
import com.cursosdedesarrollo.aplicacionrest.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alumno")
public class AlumnoController {
    AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService){
        this.alumnoService = alumnoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Alumno>> index(){
        return ResponseEntity.ok(this.alumnoService.findAll());
    }
}
