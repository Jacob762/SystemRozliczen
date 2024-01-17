package com.example.backend.util;

import com.example.backend.util.Class.Dokument;
import com.example.backend.util.Class.Ksiegowy;
import com.example.backend.util.Class.Organizacja;
import com.example.backend.util.Controlers.Aplikacja;
import com.example.backend.util.Controlers.DocumentController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.example.backend.util.Class.AdministratorOrg;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdministratorOrgControllerTests {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:8080";
    @Mock
    Organizacja organizacjaMock;

    @Test
    public void add_administrator_to_organization_test() {
        AdministratorOrg admin = new AdministratorOrg("Test");

        organizacjaMock.dodajAdministratora(admin);
        Mockito.verify(organizacjaMock).dodajAdministratora(ArgumentMatchers.any(AdministratorOrg.class));
        assertEquals(0, organizacjaMock.getAdministratorSize());

        Mockito.when(organizacjaMock.getAdministratorSize()).thenReturn(1);
        assertEquals(1, organizacjaMock.getAdministratorSize());
    }

    @Test
    public void get_administrator_from_organization_test() {
        AdministratorOrg admin = new AdministratorOrg("Test");

        Mockito.when(organizacjaMock.getAdmin(0)).thenReturn(admin);
        AdministratorOrg returnedAdmin = organizacjaMock.getAdmin(0);

        assertEquals("Test", returnedAdmin.getNazwa());
    }
    @Test
    void dodaj_administratora_test_success() throws JsonProcessingException {
        String nazwa = "TestAdmin";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(nazwa, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/admin/" + nazwa + "/0", requestEntity,String.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String retNazwa = root.path("nazwa").asText();

        assertEquals(nazwa,retNazwa);
    }

}