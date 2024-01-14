package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Ksiegowy;
import com.example.backend.util.Class.Organizacja;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/accountant")
public class KsiegowyController {
    @PostMapping()
    public ResponseEntity<Ksiegowy> dodajKsiegowego(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        String name = jsonObject.get("Nazwa").toString();
        int id = jsonObject.get("IdOrg").getAsInt();
        Ksiegowy ksiegowy = new Ksiegowy(name.substring(1, name.length() - 1));
        System.out.println(id);
        try {
            for(Organizacja org : Organizacje){
                if (org.getId()==id){
                    if (org.dodajKsiegowego(ksiegowy)) {
                        return ResponseEntity.status(HttpStatus.CREATED).body(ksiegowy);
                    }
                }
            }
        } catch (IndexOutOfBoundsException ex){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ksiegowy);
    }
    @DeleteMapping()
    public ResponseEntity<Ksiegowy> deleteKsiegowy(@RequestBody String object) {
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        int idOrg = jsonObject.get("IdOrg").getAsInt();
        int idKsieg = jsonObject.get("IdKsieg").getAsInt();
        Ksiegowy ksiegowy;
        // search for organizacja with IdOrg
        for (int i = 0; i < Organizacje.size(); i++) {
            if (Organizacje.get(i).getId() == idOrg) {
                ksiegowy = Organizacje.get(i).getKsiegowy(idKsieg);
                if (ksiegowy != null) {
                    Organizacje.get(i).usunKsiegowego(idKsieg);
                }
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<Ksiegowy> getKsiegowy(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        int idOrg = jsonObject.get("IdOrg").getAsInt();
        int idKsieg = jsonObject.get("IdKsieg").getAsInt();
        Ksiegowy ksiegowy;
        for(int i = 0; i < Organizacje.size(); i++) {
            if(Organizacje.get(i).getId()==idOrg) {
                ksiegowy = Organizacje.get(i).getKsiegowy(idKsieg);
                return new ResponseEntity<>(ksiegowy, HttpStatus.OK);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
