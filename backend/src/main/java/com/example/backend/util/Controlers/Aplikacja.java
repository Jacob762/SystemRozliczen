package com.example.backend.util.Controlers;

import com.example.backend.util.Class.AdministratorAPK;
import com.example.backend.util.Class.Organizacja;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Aplikacja {
    private List<Organizacja> Organizacje;
    private List<AdministratorAPK> Administratorzy;
    public Aplikacja(){
        Organizacje = new ArrayList<>();
        Administratorzy = new ArrayList<>();
    }

    @PostMapping("/Org/Add")
    public ResponseEntity<Organizacja> dodajOrganizacje(@RequestBody String nazwa){
        Organizacja org = new Organizacja(nazwa);
        Organizacje.add(org);
        System.out.println(Organizacje.get(org.getId()).getNazwa()+"  "+Organizacje.get(org.getId()).getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(org);
    }

    @PostMapping("/Org/Delete")
    public ResponseEntity<Organizacja> usunOrganizacje(@RequestBody int Id){

        return ResponseEntity.noContent().build();
    }

}
