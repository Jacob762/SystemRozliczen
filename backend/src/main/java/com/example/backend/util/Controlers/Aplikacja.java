package com.example.backend.util.Controlers;

import com.example.backend.util.Class.*;

import com.example.backend.util.Services.OrganizationService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@RestController
@Transactional
public class Aplikacja {
    final OrganizationService organizationService;
    protected static List<Organizacja> Organizacje;
    protected static List<AdministratorAPK> Administratorzy;

    public Aplikacja(OrganizationService organizationService){


        Organizacja organizacja = organizationService.findOrganizationById(0);
        //System.out.println(organizacja.Dokumenty.get(0).getId());

        Organizacje = new ArrayList<>();
        Administratorzy = new ArrayList<>();

       // Runtime uzywany do wywolywania funkcji przy wylaczaniu sie aplikacji:


        this.organizationService = organizationService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
    //Funkcja do zapisu danych w formacie json w folderze Data
    // https://stackoverflow.com/questions/51762784/call-a-method-before-the-java-program-closes

}
