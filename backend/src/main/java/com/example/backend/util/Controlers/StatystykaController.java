package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Organizacja;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/statystyka")
public class StatystykaController {
    @GetMapping("/{id}")
    public ResponseEntity<String> totalStatystyka(@PathVariable int id){
        JSONObject json = new JSONObject();
        for(Organizacja organizacja : Organizacje) {
            if(organizacja.getId()==id){
                float wynik = organizacja.totalStatystyka();
                int liczbaKsiegowych = organizacja.getKsiegowiSize();
                int liczbaPracownikow = organizacja.getPracownicySize();
                int dokumenty = organizacja.Dokumenty.size();
                json.put("kwota",wynik);
                json.put("dokumenty",dokumenty);
                json.put("ksiegowi",liczbaKsiegowych);
                json.put("pracownicy",liczbaPracownikow);
                return new ResponseEntity<>(json.toString(),HttpStatus.OK);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<Double> okresowaStatystyka(@RequestBody String object) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        try{
            Date poczatek = formatter.parse(jsonObject.get("poczatek").getAsString());
            Date koniec = formatter.parse(jsonObject.get("koniec").getAsString());
            int id = jsonObject.get("id").getAsInt();
            for(Organizacja organizacja : Organizacje){
                if(organizacja.getId()==id){
                    double wynik = organizacja.okresowaStatystyka(poczatek,koniec);
                    return new ResponseEntity<>(wynik,HttpStatus.OK);
                }
            }
        } catch (ParseException ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.notFound().build();
    }
}
