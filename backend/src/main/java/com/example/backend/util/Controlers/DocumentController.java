package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Dokument;
import com.example.backend.util.Class.Ksiegowy;
import com.example.backend.util.Class.Organizacja;
import com.example.backend.util.Services.AccountantService;
import com.example.backend.util.Services.DocumentService;
import com.example.backend.util.Services.OrganizationService;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@Transactional
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private AccountantService accountantService;
    @Autowired
    private OrganizationService organizationService;
    @PostMapping("/edit")
    public ResponseEntity<Dokument> edytujDokument(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        int id = jsonObject.get("idO").getAsInt();
        int idDok = jsonObject.get("idDok").getAsInt();
        float nowaKwota = jsonObject.get("kwota").getAsFloat();
        String nowaNazwa = jsonObject.get("nazwa").getAsString();
        Dokument dokument = documentService.findDocumentById(idDok);
        dokument.setKwota(nowaKwota);
        dokument.setNazwa(nowaNazwa);
        try{
            documentService.saveDocument(dokument); //spring.jackson.serialization.FAIL_ON_EMPTY_BEANS=false
            return new ResponseEntity<>(dokument,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Dokument> dodajDokument(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        String name = jsonObject.get("name").toString();
        int idKsiegowy = jsonObject.get("accountantID").getAsInt();
        float kwota = jsonObject.get("value").getAsFloat();
        int idOrg = jsonObject.get("organizationID").getAsInt();
        name = name.substring(1,name.length()-1);
        Dokument dokument = new Dokument();
        dokument.setAutor(accountantService.findAccountant(idKsiegowy));
        dokument.setKwota(kwota);
        dokument.setNazwa(name);
        dokument.setOrganizacja(organizationService.findOrganizationById(idOrg));
        documentService.saveDocument(dokument);
        return ResponseEntity.status(HttpStatus.CREATED).body(dokument);
    }
    //dokumenty.size() - 3 = ostatnie 3 dokumenty
    @GetMapping("/{idOrg}/{id}") //najpierw getdokumenty do wyswietlania wszystkich i potem konkretny getDokument
    public ResponseEntity<Dokument> getDokument(@PathVariable int idOrg, @PathVariable int id){
        try{
            Dokument dokument;
            dokument = documentService.findDocumentById(id);
            return new ResponseEntity<>(dokument,HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idOrg}")
    public ResponseEntity<List<Dokument>> getDokumenty(@PathVariable int idOrg){
        try{
            return new ResponseEntity<>(organizationService.findOrganizationById(idOrg).Dokumenty,HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/liczba/{idOrg}")
    public ResponseEntity<Integer> getSize(@PathVariable int idOrg){
        try{
            return new ResponseEntity<>(organizationService.findOrganizationById(idOrg).Dokumenty.size(),HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
        try{
            if(documentService.deleteDocument(id)) return new ResponseEntity<>(HttpStatus.OK);
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
