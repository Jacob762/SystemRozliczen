package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Dokument;
import com.example.backend.util.Class.Ksiegowy;
import com.example.backend.util.Class.Organizacja;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @PostMapping("/edit")
    public ResponseEntity<Dokument> edytujDokument(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        int id = jsonObject.get("idO").getAsInt();
        int idDok = jsonObject.get("idDok").getAsInt();
        for(Organizacja organizacja : Organizacje){
            if(organizacja.getId()==id){
                if(!organizacja.usunDokument(organizacja.getDokument(idDok))){
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
                }
            }
        }
        return dodajDokument(object);
    }

    @PostMapping() ///todo we froncie najpierw getksiegowy, wywolac jesli inne niz notfound
    public ResponseEntity<Dokument> dodajDokument(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        String name = jsonObject.get("Nazwa").toString();
        int idKsiegowy = jsonObject.get("IdK").getAsInt();
        float kwota = jsonObject.get("Kwota").getAsFloat();
        int idOrg = jsonObject.get("idO").getAsInt();
        Organizacja org = Organizacje.get(0);
        Ksiegowy ksiegowy = org.getKsiegowy(0);
        Dokument dokument;
        try{
            for(int i=0;i<Organizacje.size();i++) {
                if(Organizacje.get(i).getId()==idOrg){
                    org = Organizacje.get(i);
                    ksiegowy = org.getKsiegowy(idKsiegowy);
                    if(ksiegowy==null) return ResponseEntity.notFound().build();
                    break;
                }
            }
            dokument = new Dokument(name,kwota,ksiegowy);
            if(!org.dodajDokument(dokument)) return ResponseEntity.badRequest().build();
        } catch(IndexOutOfBoundsException ex){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(dokument);
    }
    //dokumenty.size() - 3 = ostatnie 3 dokumenty
    @GetMapping("/{idOrg}/{id}") //najpierw getdokumenty do wyswietlania wszystkich i potem konkretny getDokument
    public ResponseEntity<Dokument> getDokument(@PathVariable int idOrg, @PathVariable int id){
        Dokument dokument;
        for(Organizacja org : Organizacje){
            if(org.getId()==idOrg){
                dokument = org.getDokument(id);
                if(dokument!=null) return new ResponseEntity<>(dokument, HttpStatus.OK);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idOrg}")
    public ResponseEntity<List<Dokument>> getDokumenty(@PathVariable int idOrg){
        for(Organizacja organizacja : Organizacje){
            if(organizacja.getId()==idOrg){
                return new ResponseEntity<>(organizacja.Dokumenty,HttpStatus.OK);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/liczba/{idOrg}")
    public ResponseEntity<Integer> getSize(@PathVariable int idOrg){
        for(Organizacja organizacja : Organizacje){
            if(organizacja.getId()==idOrg){
                return new ResponseEntity<>(organizacja.Dokumenty.size(),HttpStatus.OK);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/sort/{id}")
    public ResponseEntity<Dokument> sortujDokumenty(@PathVariable int id){
        for(Organizacja organizacja : Organizacje){
            if(organizacja.getId()==id) {
                organizacja.sortujDokumenty();
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{idOrg}/{id}")
    public ResponseEntity<Dokument> usunDokument(@PathVariable int idOrg, @PathVariable int id){
        for(Organizacja organizacja : Organizacje){
            if(organizacja.getId()==idOrg){
                if(organizacja.usunDokument(organizacja.getDokument(id))) return ResponseEntity.status(HttpStatus.OK).build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
