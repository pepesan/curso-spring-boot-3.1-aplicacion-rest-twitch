package com.cursosdedesarrollo.aplicacionrest.repositories;


import com.cursosdedesarrollo.aplicacionrest.domain.Dato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class DatoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private DatoRepository datoRepository;

    @Test
    public void pruebaRepositoryIsNotNull(){
        assertNotNull(datoRepository);
    }
    @Test
    public void pruebaRepositorio(){
        Dato dato = new Dato(null, "Cadena1");
        entityManager.persist(dato);
        entityManager.flush();
        Dato encontrado = datoRepository.findByCadena("Cadena1");
        assertEquals(encontrado.getCadena(), dato.getCadena());
    }
}
