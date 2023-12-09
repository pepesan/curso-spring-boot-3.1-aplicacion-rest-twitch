package com.cursosdedesarrollo.aplicacionrest.domain;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
@Data
@Entity(name = "Alumnos")
@Table(name = "ALUMNOS")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 20, message = "el nombre debe tener mas de 3 letras y menos de 20.")
    private String nombre;
    @Column(name = "ape")
    private String apellidos;
    @Min(value = 18, message = "el usuario debe tener 18+")
    private Integer edad;
    public Alumno(){
        this.id = 0L;
        this.nombre = "";
        this.apellidos = "";
        this.edad = 0;
    }

    public Alumno(AlumnoDTO alumnoDTO){
        this.nombre = alumnoDTO.getNombre();
        this.apellidos = alumnoDTO.getApellidos();
        this.edad = alumnoDTO.getEdad();
    }
}
