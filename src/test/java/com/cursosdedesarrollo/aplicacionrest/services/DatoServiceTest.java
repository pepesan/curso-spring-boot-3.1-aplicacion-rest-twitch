package com.cursosdedesarrollo.aplicacionrest.services;


import com.cursosdedesarrollo.aplicacionrest.domain.Dato;
import com.cursosdedesarrollo.aplicacionrest.repositories.DatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class DatoServiceTest {

    @Mock
    private DatoRepository datoRepository;

    private DatoService datoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        datoService = new DatoService(datoRepository);
    }

    @Test
    public void testFindAll() {
        // Simulamos una lista de datos
        List<Dato> datos = new ArrayList<>();
        datos.add(new Dato());
        datos.add(new Dato());

        when(datoRepository.findAll()).thenReturn(datos);

        List<Dato> result = datoService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Dato dato = new Dato();
        dato.setId(id);

        when(datoRepository.findById(id)).thenReturn(Optional.of(dato));

        Optional<Dato> result = datoService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    public void testSave() {
        Dato dato = new Dato();
        dato.setCadena("Ejemplo de cadena");

        when(datoRepository.save(dato)).thenReturn(dato);

        Dato result = datoService.save(dato);

        assertNotNull(result);
        assertEquals("Ejemplo de cadena", result.getCadena());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;

        datoService.deleteById(id);

        verify(datoRepository, times(1)).deleteById(id);
    }

}
