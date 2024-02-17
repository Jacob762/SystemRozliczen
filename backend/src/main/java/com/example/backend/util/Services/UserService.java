package com.example.backend.util.Services;

import com.example.backend.util.Class.User;
import com.example.backend.util.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserService() {

    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public User findUserById(int id){return userRepository.findById(id).orElse(null);}
}
