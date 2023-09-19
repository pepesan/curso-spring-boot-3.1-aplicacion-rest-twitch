package com.cursosdedesarrollo.aplicacionrest.repositories;

import com.cursosdedesarrollo.aplicacionrest.domain.Dato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatoRepository extends CrudRepository<Dato, Long> {
    public Dato findByCadena(String Cadena);
}
