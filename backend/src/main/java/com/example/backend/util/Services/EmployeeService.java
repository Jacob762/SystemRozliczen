package com.example.backend.util.Services;

import com.example.backend.util.Class.Pracownik;
import com.example.backend.util.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean addEmployee(Pracownik pracownik){
        try{
            employeeRepository.save(pracownik);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
