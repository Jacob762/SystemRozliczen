package com.example.backend.util.Configuration;

import com.example.backend.util.Services.*;
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

    @Bean
    ApplicationAdminService applicationAdminService() {return new ApplicationAdminService();}

    @Bean
    OrganizationAdminService organizationAdminService() {return new OrganizationAdminService();}

    @Bean
    EmployeeService employeeService() {return new EmployeeService();}
}
