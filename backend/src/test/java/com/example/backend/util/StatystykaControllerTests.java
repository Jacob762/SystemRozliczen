package com.example.backend.util;


import com.example.backend.util.Class.Dokument;
import com.example.backend.util.Class.Ksiegowy;
import com.example.backend.util.Class.Organizacja;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatystykaControllerTests {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:8080";
    private int idorg;


    Organizacja organizacja = new Organizacja("Test");

    @Test
    void wygeneruj_total_statystyka_ok(){
        organizacja.dodajDokument(new Dokument("Test",22.99F, new Ksiegowy("Pawel")));
        organizacja.dodajDokument(new Dokument("Test",22.99F, new Ksiegowy("Marek")));

        assertEquals(22.99F*2, organizacja.totalStatystyka());
    }

    @Test
    void sprawdz_statystyke_http_ok_przy_utworzeniu() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>("kolejnaLosowaNazwa", headers);

        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/organization", requestEntity,String.class);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        idorg = root.path("id").asInt();

        response = restTemplate.getForEntity(URL + "/statystyka/" + idorg, String.class);
        root = mapper.readTree(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(0,root.get("kwota").asInt());
        assertEquals(0,root.get("ksiegowi").asInt());
        assertEquals(0,root.get("pracownicy").asInt());
    }

    @Test
    void sprawdz_statystyke_http_ok_po_dodaniu_dokumentu() throws JsonProcessingException {
        String object3 = "{\"Nazwa\" : \"Grzesiek\", \"idOrg\" : 0}";
        String object = "{\"Nazwa\" : \"MockDoc\", \"Kwota\" : 2137.99, \"IdK\" : 0, \"idO\" : 0}";
        String object2 = "{\"Nazwa\" : \"MockDoc\", \"Kwota\" : 22.99, \"IdK\" : 0, \"idO\" : 0}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(object3, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/accountant", requestEntity,String.class);

        requestEntity = new HttpEntity<>(object, headers);
        response = restTemplate.postForEntity(URL + "/document", requestEntity,String.class);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        requestEntity = new HttpEntity<>(object2, headers);
        response = restTemplate.postForEntity(URL + "/document", requestEntity,String.class);


        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        response = restTemplate.getForEntity(URL + "/statystyka/" + idorg, String.class);
        root = mapper.readTree(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(22.99+2137.99,root.get("kwota").asDouble());
        assertEquals(1,root.get("ksiegowi").asInt());
        assertEquals(0,root.get("pracownicy").asInt());
    }

}
