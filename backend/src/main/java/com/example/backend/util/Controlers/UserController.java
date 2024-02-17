package com.example.backend.util.Controlers;

import com.example.backend.util.Class.User;
import com.example.backend.util.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/{first_name}/{last_name}")
    public ResponseEntity<User> addUser(@PathVariable String first_name, @PathVariable String last_name){
        try{
            User user = new User();
            user.setImie(first_name);
            user.setNazwisko(last_name);
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }
}
