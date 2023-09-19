package com.cursosdedesarrollo.aplicacionrest.controllers;


import com.cursosdedesarrollo.aplicacionrest.domain.Dato;
import com.cursosdedesarrollo.aplicacionrest.services.DatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datos")
public class DatoController {

    private final DatoService datoService;

    @Autowired
    public DatoController(DatoService datoService) {
        this.datoService = datoService;
    }

    @GetMapping
    public List<Dato> getAllDatos() {
        return datoService.findAll();
    }

    @GetMapping("/{id}")
    public com.cursosdedesarrollo.aplicacionrest.domain.Dato getDatoById(@PathVariable Long id) {
        return datoService.findById(id).orElse(null);
    }

    @PostMapping
    public Dato createDato(@RequestBody Dato dato) {
        return datoService.save(dato);
    }

    @PutMapping("/{id}")
    public Dato updateDato(@PathVariable Long id, @RequestBody Dato dato) {
        dato.setId(id);
        return datoService.save(dato);
    }

    @DeleteMapping("/{id}")
    public void deleteDato(@PathVariable Long id) {
        datoService.deleteById(id);
    }
}
