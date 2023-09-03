package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.dtos.Dato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/redato")
@Slf4j
public class ResponseEntityApiController {
    public List<Dato> listado = new LinkedList<>();
    public Long lastID = 0L;

    /*
        Constructor
     */
    public ResponseEntityApiController(){
        this.listado.add(new Dato(1L,"Twitch"));
        this.lastID = 1L;
    }

    /*
        Get /api/v1/redato/
            -> Listado de datos en formato ResponseEntity
    */
    @GetMapping("/")
    ResponseEntity< List<Dato>> index(){
        return  ResponseEntity.ok(this.listado);
    }

    @GetMapping("/clear")
    List<Dato> clear(){
        this.listado  = new LinkedList<>();
        this.lastID = 0L;
        return this.listado;
    }

    /*
        Post /api/v1/redato/ -> Crear un nuevo dato
     */
    @PostMapping("/")
    ResponseEntity<Dato> create(@RequestBody Dato dato){
        dato.setId(++this.lastID);
        this.listado.add(dato);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dato);
    }

    /*
        Get /api/v1/redato/{id} -> Obtener un dato
     */
    @GetMapping("/{id}")
    ResponseEntity<Dato> show(@PathVariable("id") Long id){
        Optional<Dato> datoOpcional = this.listado
                .stream()
                .filter(dato -> dato.getId().equals(id))
                .findFirst();
        ResponseEntity<Dato> respuesta;
        /* Manera antigua
        if (datoOpcional.isPresent()){
            respuesta = ResponseEntity.ok(datoOpcional.get());
        }else {
            respuesta = ResponseEntity.notFound().build();
        }
         */
        respuesta =
                datoOpcional
                        .map(ResponseEntity::ok)
                        .orElseGet(
                                () -> ResponseEntity
                                        .notFound()
                                        .build());
        return respuesta;
    }

    /*
        Patch /api/v1/dato/{id} -> Actualizar un dato
     */
    @PatchMapping("/{id}")
    ResponseEntity<Dato> update(@PathVariable("id") Long id, @RequestBody Dato dato){
        Optional<Dato> datoOpcional = this.listado
                .stream()
                .filter(datoObtenido -> datoObtenido.getId().equals(id))
                .findFirst();
        ResponseEntity<Dato> respuesta;
        respuesta =
                datoOpcional
                        .map(datoGuardado -> {
                            datoGuardado.setCadena(dato.getCadena());
                            return ResponseEntity.ok(datoGuardado);
                        })
                        .orElseGet(
                                () -> ResponseEntity
                                        .notFound()
                                        .build());
        return respuesta;
    }
    /*
        Delete /api/v1/dato/{id} -> Eliminar un dato
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Dato> delete(@PathVariable("id") Long id){
        Optional<Dato> datoOpcional = this.listado
                .stream()
                .filter(datoObtenido -> datoObtenido.getId().equals(id))
                .findFirst();
        ResponseEntity<Dato> respuesta;
        respuesta = datoOpcional
                        .map(datoGuardado -> {
                            this.listado.remove(datoGuardado);
                            return ResponseEntity.ok(datoGuardado);
                        })
                        .orElseGet(
                                () -> ResponseEntity
                                        .notFound()
                                        .build());
        return respuesta;
    }
}
