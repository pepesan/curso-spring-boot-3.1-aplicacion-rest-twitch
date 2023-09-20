package com.cursosdedesarrollo.aplicacionrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
    @GetMapping("/hello")
    public String getString(){
        return "Hello";
    }
}
