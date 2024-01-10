package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Pracownik;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping()
    public ResponseEntity<Pracownik> dodajPracownik(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        String name = jsonObject.get("Nazwa").toString();
        int id = jsonObject.get("IdOrg").getAsInt();
        try {
            Organizacje.get(id); ///todo szukanie id organizacji
        } catch (IndexOutOfBoundsException ex){
            return ResponseEntity.notFound().build();
        }
        Pracownik pracownik = new Pracownik(name.substring(1, name.length() - 1));
        if(Organizacje.get(id).dodajPracownika(pracownik)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pracownik);
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(pracownik);
    }

    @GetMapping()
    public ResponseEntity<Pracownik> getPracownik(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        int idOrg = jsonObject.get("IdOrg").getAsInt();
        int idPrac = jsonObject.get("IdPrac").getAsInt();
        Pracownik pracownik;
        for(int i=0;i<Organizacje.size();i++){
            if(Organizacje.get(i).getId()==idOrg) {
                pracownik = Organizacje.get(i).getPracownik(idPrac);
                return new ResponseEntity<>(pracownik, HttpStatus.OK);
            }
        }
        return ResponseEntity.notFound().build();
    }

}