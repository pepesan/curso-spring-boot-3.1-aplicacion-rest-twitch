package com.cursosdedesarrollo.aplicacionrest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Dato {
    @Id
    @GeneratedValue
    private Long id;
    private String cadena;

    public Dato(){
        this.id = 0L;
        this.cadena = "";
    }
}
