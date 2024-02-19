package com.example.backend.util.Controlers;

import com.example.backend.util.Class.Organizacja;
import com.example.backend.util.Class.Pracownik;
import com.example.backend.util.Class.User;
import com.example.backend.util.Services.EmployeeService;
import com.example.backend.util.Services.OrganizationService;
import com.example.backend.util.Services.UserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend.util.Controlers.Aplikacja.Organizacje;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @Autowired
    OrganizationService organizationService;

    @PostMapping("/{user_id}/{organization_id}")
    public ResponseEntity<Pracownik> dodajPracownik(@PathVariable int user_id, @PathVariable int organization_id){
        Pracownik pracownik = new Pracownik();
        try{
            User user = userService.findUserById(user_id);
            pracownik.setUser(user);
            pracownik.setOrganizacja(organizationService.findOrganizationById(organization_id));
            pracownik.setNazwa(user.getImie() + " " + user.getNazwisko());
            if(employeeService.addEmployee(pracownik)) return new ResponseEntity<>(pracownik,HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
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
