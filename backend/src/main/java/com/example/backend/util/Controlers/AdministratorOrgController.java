package com.example.backend.util.Controlers;

import com.example.backend.util.Class.AdministratorOrg;
import com.example.backend.util.Class.Organizacja;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/admin")
public class AdministratorOrgController {

    @PostMapping("/{nazwa}/{id}")
    public ResponseEntity<AdministratorOrg> dodajAdministratoraORG(@PathVariable String nazwa, @PathVariable int id){
        for(Organizacja organizacja : Organizacje){
            if(organizacja.getId()==id){
                AdministratorOrg admin = new AdministratorOrg(nazwa);
                if(organizacja.dodajAdministratora(admin)) return ResponseEntity.status(HttpStatus.CREATED).body(admin);
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{idAdministratora}/{id}")
    public ResponseEntity<AdministratorOrg> getAdministrator(@PathVariable int id, @PathVariable int idAdministratora){
        for(Organizacja organizacja : Organizacje){
            if(organizacja.getId()==id){
                AdministratorOrg admin = organizacja.getAdmin(idAdministratora);
                if(admin!=null) return new ResponseEntity<>(admin,HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
