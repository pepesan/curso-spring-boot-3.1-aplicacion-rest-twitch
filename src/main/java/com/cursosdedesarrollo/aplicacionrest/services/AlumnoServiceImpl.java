package com.cursosdedesarrollo.aplicacionrest.services;

import com.cursosdedesarrollo.aplicacionrest.domain.Alumno;
import com.cursosdedesarrollo.aplicacionrest.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService{
    AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository){
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<Alumno> findAll() {
        return this.alumnoRepository.findAll();
    }
}
