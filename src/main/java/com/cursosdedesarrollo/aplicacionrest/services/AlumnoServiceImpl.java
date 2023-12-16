package com.cursosdedesarrollo.aplicacionrest.services;

import com.cursosdedesarrollo.aplicacionrest.domain.Alumno;
import com.cursosdedesarrollo.aplicacionrest.domain.AlumnoDTO;
import com.cursosdedesarrollo.aplicacionrest.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Alumno save(Alumno alumno) {
        this.alumnoRepository.save(alumno);
        return alumno;
    }

    @Override
    public Optional<Alumno> findById(Long id) {
        return this.alumnoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.alumnoRepository.deleteById(id);
    }
}
