package com.example.backend.util.Services;

import com.example.backend.util.Repositories.OrganizationAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationAdminService {

    @Autowired
    private OrganizationAdminRepository organizationAdminRepository;
}
