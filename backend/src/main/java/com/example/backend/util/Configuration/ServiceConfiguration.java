package com.example.backend.util.Configuration;

import com.example.backend.util.Services.AccountantService;
import com.example.backend.util.Services.DocumentService;
import com.example.backend.util.Services.OrganizationService;
import com.example.backend.util.Services.UserService;
import org.springframework.context.annotation.*;



@Configuration
public class ServiceConfiguration {

    @Bean
    UserService userService(){
        return new UserService();
    }

    @Bean
    DocumentService documentService(){
        return new DocumentService();
    }

    @Bean
    AccountantService accountantService() {return new AccountantService();}

    @Bean
    OrganizationService organizationService() {return new OrganizationService();}
}
