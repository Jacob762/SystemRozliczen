package com.example.backend.util.Controlers;

import com.example.backend.util.Class.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Aplikacja {
    private List<Organizacja> Organizacje;
    private List<AdministratorAPK> Administratorzy;
    public Aplikacja(){
        Organizacje = new ArrayList<>();
        Administratorzy = new ArrayList<>();

        try {
            readDataFromJson();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Runtime uzywany do wywolywania funkcji przy wylaczaniu sie aplikacji:
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                   saveDataToJson();
                } catch (IOException e) {
                   throw new RuntimeException(e);
                }
            }
        }));
    }

    @PostMapping("/Org")
    public ResponseEntity<Organizacja> dodajOrganizacje(@RequestBody String nazwa) {
        Organizacja org = new Organizacja(nazwa);
        Organizacje.add(org);
        System.out.println(Organizacje.get(org.getId()).getNazwa()+"  "+Organizacje.get(org.getId()).getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(org);
    }

    @DeleteMapping("/Org")
    public ResponseEntity<Organizacja> usunOrganizacje(@RequestBody int Id){
        Organizacje.remove(Id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/User")
    public ResponseEntity<Pracownik> dodajPracownik(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        String name = jsonObject.get("Nazwa").toString();
        int id = jsonObject.get("IdOrg").getAsInt();
        try {
            Organizacje.get(id);
        } catch (IndexOutOfBoundsException ex){
            return ResponseEntity.notFound().build();
        }
        Pracownik pracownik = new Pracownik(name.substring(1, name.length() - 1));
        if(Organizacje.get(id).dodajPracownika(pracownik)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pracownik);
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(pracownik);
    }

    @PostMapping("/accountant")
    public ResponseEntity<Ksiegowy> dodajKsiegowego(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        String name = jsonObject.get("Nazwa").toString();
        int id = jsonObject.get("IdOrg").getAsInt();
        try {
            Organizacje.get(id);
        } catch (IndexOutOfBoundsException ex){
            return ResponseEntity.notFound().build();
        }
        Ksiegowy ksiegowy = new Ksiegowy(name.substring(1, name.length() - 1));
        if (Organizacje.get(id).dodajKsiegowego(ksiegowy)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ksiegowy);
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ksiegowy);
    }

    @PostMapping("/Adm/Add")
    public ResponseEntity<AdministratorAPK> dodajAdministratoraAPK(@RequestBody String object){
        AdministratorAPK admin = new AdministratorAPK(object);
        Administratorzy.add(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }

    @PostMapping("/Doc")
    public ResponseEntity<Dokument> dodajDokument(@RequestBody String object){
        JsonObject jsonObject = JsonParser.parseString(object)
                .getAsJsonObject();
        String name = jsonObject.get("Nazwa").toString();
        int idKsiegowy = jsonObject.get("IdK").getAsInt();
        float kwota = jsonObject.get("Kwota").getAsFloat();
        int idOrg = jsonObject.get("IdO").getAsInt();
        Organizacja org = Organizacje.get(0);
        Ksiegowy ksiegowy = org.getKsiegowy(0);
        Dokument dokument;
        try{
            for(int i=0;i<Organizacje.size();i++) {
                if(Organizacje.get(i).getId()==idOrg){
                    org = Organizacje.get(i);
                    ksiegowy = org.getKsiegowy(idKsiegowy);
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

    @GetMapping("/User")
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


    //Funkcja do zapisu danych w formacie json w folderze Data
    // https://stackoverflow.com/questions/51762784/call-a-method-before-the-java-program-closes
    private void saveDataToJson() throws IOException {
        System.out.println("Saving data...");

        Gson gson = new Gson();
        
        String org = gson.toJson(Organizacje);
        try (FileWriter fw = new FileWriter("src/main/java/com/example/backend/util/Data/organizacje.json")) {
            fw.write(org);
            fw.close();
        } catch (Exception e) {
            throw e;
        }
        
        String adm = gson.toJson(Administratorzy);
        try (FileWriter fw = new FileWriter("src/main/java/com/example/backend/util/Data/administratorzy.json")) {
            fw.write(adm);
            fw.close();
        } catch (Exception e) {
            throw e;
        }
        
        System.out.println("Data saved.");
    } // cos nie dziala?

    //Funkcja do wczytywania danych w formacie json z folderu Data, uruchamiana przy wlaczeniu sie aplikacji
    private void readDataFromJson() throws IOException {
        Gson gson = new Gson();
        
        String org = new String("src/main/java/com/example/backend/util/Data/organizacje.json");
        try (JsonReader reader = new JsonReader(new FileReader(org))) {
            Organizacja[] data = gson.fromJson(reader, Organizacja[].class);
            if (data.length > 0)
            {
                Organizacje = new ArrayList<Organizacja>(Arrays.asList(data));
            }
        } catch (Exception e) {
            throw e;
        }

        String adm = new String("src/main/java/com/example/backend/util/Data/administratorzy.json");
        try (JsonReader reader = new JsonReader(new FileReader(adm))) {
            AdministratorAPK[] data = gson.fromJson(reader, AdministratorAPK[].class);
            if (data.length > 0)
            {
                Administratorzy = new ArrayList<AdministratorAPK>(Arrays.asList(data));
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
