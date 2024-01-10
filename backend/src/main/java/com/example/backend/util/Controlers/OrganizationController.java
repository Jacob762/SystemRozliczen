package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Organizacja;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @PostMapping
    public ResponseEntity<Organizacja> dodajOrganizacje(@RequestBody String nazwa) {
        Organizacja org = new Organizacja(nazwa);
        Organizacje.add(org);
        return ResponseEntity.status(HttpStatus.CREATED).body(org);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Organizacja> usunOrganizacje(@PathVariable int id){
        for(Organizacja organizacja : Organizacje){
            if(organizacja.getId()==id){
                Organizacje.remove(organizacja);
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizacja> getOrganizacja(@PathVariable int id){
        for(int i=0;i<Organizacje.size();i++){
            if(Organizacje.get(i).getId()==id) return new ResponseEntity<>(Organizacje.get(i), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


}
