package com.cursosdedesarrollo.aplicacionrest.services;

import com.cursosdedesarrollo.aplicacionrest.domain.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlumnoService {
    List<Alumno> findAll();
}
