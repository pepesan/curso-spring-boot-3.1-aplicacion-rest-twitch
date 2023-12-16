package com.cursosdedesarrollo.aplicacionrest.services;

import com.cursosdedesarrollo.aplicacionrest.domain.Alumno;
import com.cursosdedesarrollo.aplicacionrest.domain.AlumnoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlumnoService {
    List<Alumno> findAll();

    Alumno save(Alumno alumno);

    Optional<Alumno> findById(Long id);

    void deleteById(Long id);
}
