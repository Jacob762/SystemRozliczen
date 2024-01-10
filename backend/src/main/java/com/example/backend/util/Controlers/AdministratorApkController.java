package com.example.backend.util.Controlers;

import com.example.backend.util.Class.AdministratorAPK;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminapk")
public class AdministratorApkController {
    @PostMapping
    public ResponseEntity<AdministratorAPK> dodajAdministratoraAPK(@RequestBody String object){
        AdministratorAPK admin = new AdministratorAPK(object);
        Aplikacja.Administratorzy.add(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }
}
