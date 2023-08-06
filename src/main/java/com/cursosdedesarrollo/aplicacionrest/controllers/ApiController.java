package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.dtos.Dato;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dato")
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
}
