package com.cursosdedesarrollo.aplicacionrest.services;

import com.cursosdedesarrollo.aplicacionrest.dtos.SchoolCreateUpdateDTO;
import com.cursosdedesarrollo.aplicacionrest.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cursosdedesarrollo.aplicacionrest.domain.School;

import java.util.List;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;

    @Autowired
    SchoolService(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    public List<School> findAll(){
        return this.schoolRepository.findAll();
    }

    public School save(SchoolCreateUpdateDTO schoolDTO) {
        School school = new School(schoolDTO);
        school = this.schoolRepository.save(school);
        return school;
    }
}
