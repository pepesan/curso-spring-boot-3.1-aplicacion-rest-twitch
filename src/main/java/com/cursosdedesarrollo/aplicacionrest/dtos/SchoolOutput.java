package com.cursosdedesarrollo.aplicacionrest.dtos;

import com.cursosdedesarrollo.aplicacionrest.domain.School;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SchoolOutput extends SchoolCreateUpdateDTO{
    private Long id;

    public SchoolOutput(School school){
        this.id = school.getId();
        this.setName(school.getName());
        this.setAddress(school.getAddress());
        this.setAlumnSize(school.getAlumnSize());
    }
}
