package com.example.backend.util.Services;

import com.example.backend.util.Class.Organizacja;
import com.example.backend.util.Repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;

    public OrganizationService(){}

    public boolean createOrganization(Organizacja organizacja){
        try{
            organizationRepository.save(organizacja);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public Organizacja findOrganizationById(int id){
        return organizationRepository.findById(id).orElse(null);
    }

    public boolean deleteOrganization(int id){
        try{
            organizationRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
