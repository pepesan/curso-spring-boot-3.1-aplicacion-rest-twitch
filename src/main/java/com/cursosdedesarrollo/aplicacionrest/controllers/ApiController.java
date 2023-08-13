package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.dtos.Dato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dato")
@Slf4j
public class ApiController {
    public List<Dato> listado = new LinkedList<>();
    public Long lastID = 0L;

    /*
        Constructor
     */
    public ApiController(){
        this.listado.add(new Dato(1L,"Twitch"));
        this.lastID = 1L;
    }

    /*
        Get /api/v1/dato/ -> Listado de datos
     */
    @GetMapping("/")
    List<Dato> index(){
        return  this.listado;
    }

    /*
        Post /api/v1/dato/ -> Crear un nuevo dato
     */
    @PostMapping("/")
    Dato create(@RequestBody Dato dato){
        dato.setId(++this.lastID);
        this.listado.add(dato);
        return dato;
    }

    /*
        Get /api/v1/dato/{id} -> Obtener un dato
     */
    @GetMapping("/{id}")
    Dato show(@PathVariable("id") Long id){
        return this.listado
                .stream()
                .filter(dato -> dato.getId().equals(id))
                .findFirst().orElse(new Dato());
    }

    /*
        Patch /api/v1/dato/{id} -> Actualizar un dato
     */
    @PatchMapping("/{id}")
    Dato update(@PathVariable("id") Long id, @RequestBody Dato dato){
        return this.listado
                .stream()
                .filter(datoGuardado -> datoGuardado.getId().equals(id))
                .findFirst()
                .map(datoGuardado -> {
                      datoGuardado.setCadena(dato.getCadena());
                      return datoGuardado;
                }).orElse(new Dato());
    }
    /*
        Delete /api/v1/dato/{id} -> Eliminar un dato
     */
    @DeleteMapping("/{id}")
    Dato delete(@PathVariable("id") Long id){
        // busca el elemento en listado, lo elimina y lo devuelve
        return this.listado
               .stream()
               .filter(datoGuardado -> datoGuardado.getId().equals(id))
               .findFirst()
               .map(datoGuardado -> {
                      this.listado.remove(datoGuardado);
                      return datoGuardado;
                }).orElse(new Dato());
    }
}
