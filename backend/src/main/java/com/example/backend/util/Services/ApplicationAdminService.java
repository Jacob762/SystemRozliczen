package com.example.backend.util.Services;

import com.example.backend.util.Repositories.ApplicationAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationAdminService {

    @Autowired
    private ApplicationAdminRepository applicationAdminRepository;
}
