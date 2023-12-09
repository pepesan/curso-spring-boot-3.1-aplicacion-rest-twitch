package com.cursosdedesarrollo.aplicacionrest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
@Data
public class AlumnoDTO {
    @NotNull
    @Size(min = 3, max = 20, message = "el nombre debe tener mas de 3 letras y menos de 20.")
    private String nombre;
    // validamos los apellido
    @NotNull(message = "los apellidos no pueden estar vacios")
    private String apellidos;
    @Min(value = 18, message = "el usuario debe tener 18+")
    private Integer edad;
}
