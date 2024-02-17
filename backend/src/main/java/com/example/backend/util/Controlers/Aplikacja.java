package com.example.backend.util.Controlers;

import com.example.backend.util.Class.*;

import com.example.backend.util.Services.OrganizationService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@RestController
@Transactional
public class Aplikacja {
    final OrganizationService organizationService;
    protected static List<Organizacja> Organizacje;
    protected static List<AdministratorAPK> Administratorzy;

    public Aplikacja(OrganizationService organizationService){


        Organizacja organizacja = organizationService.findOrganizationById(0);
        //System.out.println(organizacja.Dokumenty.get(0).getId());

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
        this.organizationService = organizationService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
    //Funkcja do zapisu danych w formacie json w folderze Data
    // https://stackoverflow.com/questions/51762784/call-a-method-before-the-java-program-closes
    private void saveDataToJson() throws IOException {
        System.out.println("Saving data...");

        Gson gson = new Gson();
        
        String org = gson.toJson(Organizacje);
        try (FileWriter fw = new FileWriter("backend/src/main/java/com/example/backend/util/Data/organizacje.json")) {
            fw.write(org);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String adm = gson.toJson(Administratorzy);
        try (FileWriter fw = new FileWriter("backend/src/main/java/com/example/backend/util/Data/administratorzy.json")) {
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
        try (FileWriter fw = new FileWriter("backend/src/main/java/com/example/backend/util/Data/counters.json")) {
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
        
        String org = "backend/src/main/java/com/example/backend/util/Data/organizacje.json";
        try (JsonReader reader = new JsonReader(new FileReader(org))) {
            Organizacja[] data = gson.fromJson(reader, Organizacja[].class);
            if (data.length > 0)
            {
                Organizacje = new ArrayList<Organizacja>(Arrays.asList(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String adm = "backend/src/main/java/com/example/backend/util/Data/administratorzy.json";

        try (JsonReader reader = new JsonReader(new FileReader(adm))) {
            AdministratorAPK[] data = gson.fromJson(reader, AdministratorAPK[].class);
            if (data.length > 0)
            {
                Administratorzy = new ArrayList<AdministratorAPK>(Arrays.asList(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String countPath = "backend/src/main/java/com/example/backend/util/Data/counters.json";
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
