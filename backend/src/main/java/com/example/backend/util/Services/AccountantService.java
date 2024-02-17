package com.example.backend.util.Services;

import com.example.backend.util.Class.Ksiegowy;
import com.example.backend.util.Repositories.AccountantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountantService {
    @Autowired
    private AccountantRepository accountantRepository;

    public AccountantService() {

    }

    public Ksiegowy findAccountant(int id){
        return accountantRepository.findById(id).orElse(null);
    }

    public void addAccountant(Ksiegowy ksiegowy){
        accountantRepository.save(ksiegowy);
    }
}
