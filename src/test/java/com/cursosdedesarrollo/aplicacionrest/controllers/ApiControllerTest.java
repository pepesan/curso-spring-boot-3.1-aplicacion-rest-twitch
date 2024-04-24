package com.cursosdedesarrollo.aplicacionrest.controllers;

import com.cursosdedesarrollo.aplicacionrest.dtos.Dato;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.ArrayList;
import java.util.List;

import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    public String basePath = "/api/v1/dato";

    @BeforeEach
    public void clearRestData() throws Exception {
        System.out.println("limpiando");
        mockMvc.perform(
                get(basePath+"/clear").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void testListShouldReturnOkResult() throws Exception {
        List<Dato> listadoEsperado= new ArrayList<Dato>();
        mockMvc.perform(
                get(basePath+"/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // comprobación del tipo de contenido
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // comprobación del contenido
                .andExpect(content().json(mapper.writeValueAsString(listadoEsperado)));
    }

    @Test
    void testAddShouldReturnDato() throws Exception {
        Dato objetoEnviado= new Dato(0L,"valor");
        Dato objetoEsperado= new Dato(1L,"valor");
        mockMvc.perform(
                        post(basePath+"/")
                        .content(asJsonString(objetoEnviado))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(objetoEsperado)));
    }
    @Test
    void testGetByIDShouldReturnDato() throws Exception {
        Dato datoEsperado= new Dato(1L,"valor");
        // metemos el dato antes de consultarlo
        testAddShouldReturnDato();
        mockMvc.perform(
                        get(basePath+"/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(datoEsperado)));
    }
    @Test
    void testGetByIDShouldNotReturnDato() throws Exception {
        Dato datoEsperado= new Dato();
        // no metemos el dato antes de consultarlo
        mockMvc.perform(
                        get(basePath+"/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(datoEsperado)));
    }
    @Test
    void testUpdateShouldReturnDato() throws Exception {
        Dato datoEnviado= new Dato(0L,"valor1");
        Dato datoEsperado = new Dato(1L,"valor1");
        // metemos el dato antes de modificarlo
        testAddShouldReturnDato();
        mockMvc.perform(
                        patch(basePath+"/1")
                        .content(asJsonString(datoEnviado))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(datoEsperado)));
    }

    @Test
    void testUpdateShouldNotReturnDato() throws Exception {
        Dato datoEnviado= new Dato(0L,"valor1");
        Dato datoEsperado = new Dato();
        // no metemos el dato antes de modificarlo
        mockMvc.perform(
                        patch(basePath+"/1")
                        .content(asJsonString(datoEnviado))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(datoEsperado)));
    }

    @Test
    void testRemoveByIDShouldReturnDato() throws Exception {
        Dato datoEsperado = new Dato(1L,"valor");
        // metemos el dato antes de borrarlo
        testAddShouldReturnDato();
        mockMvc.perform(
                        delete(basePath+"/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(datoEsperado)));
    }

    @Test
    void testRemoveByIDShouldNotReturnDato() throws Exception {
        Dato datoEsperado = new Dato();
        // no metemos el dato antes de borrarlo
        mockMvc.perform(
                        delete(basePath+"/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(datoEsperado)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
