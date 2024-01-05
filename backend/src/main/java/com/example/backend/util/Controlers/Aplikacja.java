package com.example.backend.util.Controlers;

import com.example.backend.util.Class.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import org.apache.catalina.connector.Response;
import org.aspectj.weaver.ast.Or;
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

//       try {
//           readDataFromJson();
//       } catch (Exception e) {
//           System.out.println(e.getMessage());
//       }


       // Runtime uzywany do wywolywania funkcji przy wylaczaniu sie aplikacji:
//       Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//           public void run() {
//               try {
//                  saveDataToJson();
//               } catch (IOException e) {
//                  throw new RuntimeException(e);
//               }
//           }
//       }));
    }

    @PostMapping("/organization")
    public ResponseEntity<Organizacja> dodajOrganizacje(@RequestBody String nazwa) {
        Organizacja org = new Organizacja(nazwa);
        Organizacje.add(org);
        //System.out.println(Organizacje.get(org.getId()).getNazwa()+"  "+Organizacje.get(org.getId()).getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(org);
    }

    @DeleteMapping("/organization")
    public ResponseEntity<Organizacja> usunOrganizacje(@RequestBody int Id){
        Organizacje.remove(Id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user")
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

    @PostMapping("/adminapk")
    public ResponseEntity<AdministratorAPK> dodajAdministratoraAPK(@RequestBody String object){
        AdministratorAPK admin = new AdministratorAPK(object);
        Administratorzy.add(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }

    @PostMapping("/document")
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

    @GetMapping("/user")
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

    @DeleteMapping("/accountant")
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

    @GetMapping("/accountant")
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
            e.printStackTrace();
        }
        
        String adm = gson.toJson(Administratorzy);
        try (FileWriter fw = new FileWriter("src/main/java/com/example/backend/util/Data/administratorzy.json")) {
            fw.write(adm);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        JsonObject counters = new JsonObject();
        counters.addProperty("AdmApkCount", AdministratorAPK.getCount());
        counters.addProperty("AdmOrgCount", AdministratorOrg.getCount());
        counters.addProperty("DokumentCount", Dokument.getCount());
        counters.addProperty("KsiegowyCount", Ksiegowy.getCount());
        counters.addProperty("OrganizacjaCount", Organizacja.getCount());
        counters.addProperty("PracownikCount", Pracownik.getCount());
        counters.addProperty("WykresCount", Wykres.getCount());
        try (FileWriter fw = new FileWriter("src/main/java/com/example/backend/util/Data/counters.json")) {
            fw.write(counters.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Data saved.");
    } 

    //Funkcja do wczytywania danych w formacie json z folderu Data, uruchamiana przy wlaczeniu sie aplikacji
    private void readDataFromJson(){
        Gson gson = new Gson();
        
        String org = "src/main/java/com/example/backend/util/Data/organizacje.json";
        try (JsonReader reader = new JsonReader(new FileReader(org))) {
            Organizacja[] data = gson.fromJson(reader, Organizacja[].class);
            if (data.length > 0)
            {
                Organizacje = new ArrayList<Organizacja>(Arrays.asList(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String adm = "src/main/java/com/example/backend/util/Data/organizacje.json";

        try (JsonReader reader = new JsonReader(new FileReader(adm))) {
            AdministratorAPK[] data = gson.fromJson(reader, AdministratorAPK[].class);
            if (data.length > 0)
            {
                Administratorzy = new ArrayList<AdministratorAPK>(Arrays.asList(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String countPath = "src/main/java/com/example/backend/util/Data/counters.json";
        try (JsonReader reader = new JsonReader(new FileReader(countPath))) {
            try {
            JsonObject counts = JsonParser.parseReader(reader).getAsJsonObject();
            
            int tempCount;
            tempCount = counts.get("AdmApkCount").getAsInt();
            AdministratorAPK.setCount(tempCount);

            tempCount = counts.get("AdmOrgCount").getAsInt();
            AdministratorOrg.setCount(tempCount);

            tempCount = counts.get("DokumentCount").getAsInt();
            Dokument.setCount(tempCount);

            tempCount = counts.get("KsiegowyCount").getAsInt();
            Ksiegowy.setCount(tempCount);
            
            tempCount = counts.get("OrganizacjaCount").getAsInt();
            Organizacja.setCount(tempCount);
            
            tempCount = counts.get("PracownikCount").getAsInt();
            Pracownik.setCount(tempCount);

            tempCount = counts.get("WykresCount").getAsInt();
            Wykres.setCount(tempCount);

            } catch (Exception e) {
                System.out.println("Failed to parse counters");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
