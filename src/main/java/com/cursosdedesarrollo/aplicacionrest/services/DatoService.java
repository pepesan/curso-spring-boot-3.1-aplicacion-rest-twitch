package com.cursosdedesarrollo.aplicacionrest.services;

import com.cursosdedesarrollo.aplicacionrest.domain.Dato;
import com.cursosdedesarrollo.aplicacionrest.repositories.DatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatoService {

    private final DatoRepository datoRepository;

    @Autowired
    public DatoService(DatoRepository datoRepository) {
        this.datoRepository = datoRepository;
    }

    public List<Dato> findAll() {
        return (List<Dato>) datoRepository.findAll();
    }

    public Optional<Dato> findById(Long id) {
        return datoRepository.findById(id);
    }

    public Dato save(Dato dato) {
        return datoRepository.save(dato);
    }

    public void deleteById(Long id) {
        datoRepository.deleteById(id);
    }

    public void clear() {
        datoRepository.deleteAll();
    }
}

