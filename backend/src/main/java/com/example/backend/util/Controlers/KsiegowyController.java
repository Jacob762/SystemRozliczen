package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Ksiegowy;
import com.example.backend.util.Class.Organizacja;
import com.example.backend.util.Class.User;
import com.example.backend.util.Services.AccountantService;
import com.example.backend.util.Services.OrganizationService;
import com.example.backend.util.Services.UserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/accountant")
public class KsiegowyController {

    @Autowired
    AccountantService accountantService;
    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;

    @PostMapping("/{user_id}/{organization_id}")
    public ResponseEntity<Ksiegowy> dodajKsiegowego(@PathVariable int user_id, @PathVariable int organization_id){
        Ksiegowy ksiegowy = new Ksiegowy();
        try {
            User user = userService.findUserById(user_id);
            ksiegowy.setUser(user);
            ksiegowy.setOrganizacja(organizationService.findOrganizationById(organization_id));
            ksiegowy.setNazwa(user.getImie() + " " + user.getNazwisko());
            accountantService.addAccountant(ksiegowy);
        } catch (Exception ex){
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

    @GetMapping("/{IdK}")
    public ResponseEntity<Ksiegowy> getKsiegowy(@PathVariable int IdK){
        Ksiegowy ksiegowy;
        try {
            return new ResponseEntity<>(accountantService.findAccountant(IdK),HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
