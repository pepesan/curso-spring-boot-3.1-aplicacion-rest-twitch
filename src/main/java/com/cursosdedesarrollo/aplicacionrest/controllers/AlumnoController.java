package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.domain.Alumno;
import com.cursosdedesarrollo.aplicacionrest.domain.AlumnoDTO;
import com.cursosdedesarrollo.aplicacionrest.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // Add a Alumno Object to the service
    @PostMapping("/")
    public ResponseEntity<Alumno> create(@RequestBody AlumnoDTO alumnoDTO) {
        Alumno alumno = new Alumno(alumnoDTO);
        this.alumnoService.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alumno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> show(@PathVariable Long id){
        return ResponseEntity.ok(this.alumnoService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> update(@PathVariable Long id, @RequestBody AlumnoDTO alumnoDTO){
        Alumno alumno = this.alumnoService.findById(id);
        if (alumno != null) {
            alumno.setNombre(alumnoDTO.getNombre());
            alumno.setApellidos(alumnoDTO.getApellidos());
            alumno.setEdad(alumnoDTO.getEdad());
            this.alumnoService.save(alumno);
        }
        return ResponseEntity.ok(alumno);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Alumno> destroy(@PathVariable Long id){
        Alumno alumno = this.alumnoService.findById(id);
        if (alumno != null) {
            this.alumnoService.deleteById(id);
        }
        return ResponseEntity.ok(alumno);
    }
}
