package com.cursosdedesarrollo.aplicacionrest.acceptance;

import com.cursosdedesarrollo.aplicacionrest.domain.Dato;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatoControllerAcceptanceTest {

    @LocalServerPort
    private int port;

    private Long insertedID;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private String getUrl(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    public void testGetAllDatos() {
        ResponseEntity<List> response =
                restTemplate.getForEntity(getUrl("/datos"), List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetDatoById() {
        testCreateDato();
        ResponseEntity<Dato> response =
                restTemplate.getForEntity(getUrl("/datos/" + this.insertedID), Dato.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(this.insertedID, response.getBody().getId());
    }

    @Test
    public void testCreateDato() {
        Dato dato = new Dato();
        dato.setCadena("Nuevo dato");

        ResponseEntity<Dato> response =
                restTemplate.postForEntity(getUrl("/datos"), dato, Dato.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Nuevo dato", response.getBody().getCadena());
        this.insertedID = response.getBody().getId();
    }

    @Test
    public void testUpdateDato() {
        testCreateDato();
        Dato dato = new Dato();
        dato.setCadena("Dato actualizado");

        restTemplate.put(getUrl("/datos/" + this.insertedID), dato);

        ResponseEntity<Dato> response =
                restTemplate.getForEntity(getUrl("/datos/" + this.insertedID), Dato.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(this.insertedID, response.getBody().getId());
        assertEquals("Dato actualizado", response.getBody().getCadena());
    }

    @Test
    public void testDeleteDato() {
        testCreateDato();

        restTemplate.delete(getUrl("/datos/" + this.insertedID));

        ResponseEntity<Dato> response =
                restTemplate.getForEntity(getUrl("/datos/" + this.insertedID), Dato.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
}
