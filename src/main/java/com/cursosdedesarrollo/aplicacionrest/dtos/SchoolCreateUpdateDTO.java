package com.cursosdedesarrollo.aplicacionrest.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SchoolCreateUpdateDTO {
    @NotNull
    @NotBlank
    private String name;

    @PositiveOrZero
    private Long alumnSize;

    @Size(max = 50)
    private String address;
}
