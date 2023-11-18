package com.cursosdedesarrollo.aplicacionrest.repositories;

import com.cursosdedesarrollo.aplicacionrest.domain.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository
        extends JpaRepository<Alumno, Long> {
}
