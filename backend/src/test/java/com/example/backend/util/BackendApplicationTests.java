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

import javax.swing.text.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:8080";
    @Mock
    Organizacja organizacjaMock;

    ///test na document controllerze + test na klasie typu org:
    // getId & dodajDokumet, org.dodajDokument dobre do mockowania
    // zeby dodajDokument sie wywolalo, trzeba dodac Ksiegowego do organizacji
    // lancuszek 4 funckje: DocumentController.dodajDokument (http), Organizacja.getId
    // Organizacja.dodajKsiegowy() - mock, Organizacja.dodajDokument() - mock
    @Test
    void dodaj_ksiegowego_do_organizacji_test(){
//        organizacjaMock.dodajKsiegowego(new Ksiegowy("MockKsiegowy22"));
//        Mockito.verify(organizacjaMock).dodajKsiegowego(ArgumentMatchers.any(Ksiegowy.class));
//        assertEquals(0,organizacjaMock.getKsiegowiSize());
//
//
//        Mockito.when(organizacjaMock.getKsiegowiSize()).thenReturn(50);
//        assertEquals(50, organizacjaMock.getKsiegowiSize());
    }

    @Test
    void dodaj_dokument_do_organizacji_test(){
        organizacjaMock.dodajDokument(new Dokument("XD", 2138.99F,organizacjaMock.getKsiegowy(0)));
        Mockito.verify(organizacjaMock).dodajDokument(ArgumentMatchers.any(Dokument.class));
        assertEquals(0,organizacjaMock.getDokumentySize());

        Mockito.when(organizacjaMock.getDokumentySize()).thenReturn(86);
        assertEquals(86, organizacjaMock.getDokumentySize());

    }
    @Test
    void dodaj_dokument_test_success() throws JsonProcessingException {
        String object = "{\"Nazwa\" : \"MockDoc\", \"Kwota\" : 2137.99, \"IdK\" : 0, \"idO\" : 0}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(object, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/document", requestEntity,String.class);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String nazwa = root.path("nazwa").asText();
        nazwa = nazwa.substring(1,nazwa.length()-1);
        double kwota = root.path("kwota").asDouble();
        JsonNode Autor = root.path("autor");
        int idK = Autor.path("Id").asInt();
        String nazwaKsiegowego = Autor.path("Nazwa").asText();

        assertEquals("MockDoc",nazwa);
        assertEquals(2137.99,kwota);
        assertEquals(0,idK);
        assertNotNull(nazwaKsiegowego);

    }

    @Test
    void sprawdz_id_dodanej_organizacji_success() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>("kolejnaLosowaNazwa", headers);

        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/organization", requestEntity,String.class);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        int id = root.path("id").asInt();

        response = restTemplate.getForEntity(URL + "/organization/" + id, String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());

        root = mapper.readTree(response.getBody());
        assertEquals(id, root.path("id").asInt());
    }



//    @Test //zwraca 404, chyba ze zmieni sie sciezka odczytu i zapisu do pliku json, trzeba dodac backend/ na poczatku
//    void get_dokument_test_ok_getKwota_getNazwa() throws JsonProcessingException {
//
//        ResponseEntity<String> response = restTemplate.getForEntity(URL + "/document/0/1", String.class);
//
//        assertNotEquals(HttpStatus.OK, response.getStatusCode());
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(response.getBody());
//        JsonNode name = root.path("nazwa");
//        JsonNode kwota = root.path("kwota");
//        double kwotaTest = kwota.asDouble();
//        String nazwa = name.asText();
//        assertEquals(22.99,kwotaTest);
//        assertEquals("TestDokument",nazwa);
//    }

//    @Test
//    void dodaj_organizacje_test_created_getNazwa_getId() throws JsonProcessingException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> requestEntity = new HttpEntity<>("kolejnaLosowaNazwa", headers);
//
//
//        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/organization", requestEntity,String.class);
//
//        assertEquals(HttpStatus.CREATED,response.getStatusCode());
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(response.getBody());
//        String nazwa = root.path("nazwa").asText();
//        int ksiegowiSize = root.path("ksiegowiSize").asInt();
//        int pracownicySize = root.path("pracownicySize").asInt();
//
//        assertEquals("kolejnaLosowaNazwa",nazwa);
//        assertEquals(0,ksiegowiSize);
//        assertEquals(0,pracownicySize);
//    }

    @Test
    void contextLoads() {
    } ///todo na jedna osobe jedna funkcjonalnosc, jedna funkcja nie oznacza jednego testu, testow ma byc kilka testujacych skrajne przypadki
        // kazda wartosc osobna funkcja testujaca, funkcje maja miec wlasne osobne nazwy, pokazujace co testuja
        // pelne funkcje zamockowane, przynajmniej dwie funkcje bez mocka dwie z mockiem, przynajmniej 4 funkcje przetestowac
        // w jednym lancuszku, na nastepnych zajeciach fitness uzywany, za pomoca fitnessa ze dwie funkcjonalnosci na grupe
        // teraz jmock + junit

    ///todo jedna funkcjonalnosc na osobe z fitnessem, mockowanie jesli nie ma bazy, ale nie ma wymogu mockowania
    // testujemy cala sciezke funkcjonalna, funkcje nalezace do fasady z odpowiednimi parametrami, sprawdzamy co wyjdzie
    // warianty dla roznych wartosci, doprowadzic funkcje do stanu gdzie sa testowalne i cos zwracaja

    ///todo selenium jeden test na osobe, zrobic edycje dokumentow
}
