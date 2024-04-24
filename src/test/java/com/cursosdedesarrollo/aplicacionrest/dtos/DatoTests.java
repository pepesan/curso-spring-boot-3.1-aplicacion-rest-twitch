package com.cursosdedesarrollo.aplicacionrest.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatoTests {
    @Test
    public void checkContructorSinParametros(){
        Dato dato = new Dato();
        assertNotNull(dato);
        assertEquals(dato.getId(), 0, "El Id debería ser 0");
        assertEquals(dato.getCadena(), "", "La cadena debería ser ''");
    }
    @Test
    public void checkContructorConParametros(){
        Dato dato = new Dato(1L, "Twitch");
        assertNotNull(dato);
        assertEquals(dato.getId(), 1L, "El Id debería ser 1L");
        assertEquals(dato.getCadena(), "Twitch", "La cadena debería ser \"Twitch\"");
    }
}
