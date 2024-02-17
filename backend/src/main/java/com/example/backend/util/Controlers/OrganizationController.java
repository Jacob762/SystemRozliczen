package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Organizacja;
import com.example.backend.util.Services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @PostMapping("/{name}")
    public ResponseEntity<Organizacja> dodajOrganizacje(@PathVariable String name) {
        try{
            Organizacja organizacja = new Organizacja();
            organizacja.setNazwa(name);
            organizationService.createOrganization(organizacja);
            return new ResponseEntity<>(organizacja,HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
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
        try{
            return new ResponseEntity<>(organizationService.findOrganizationById(id), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
