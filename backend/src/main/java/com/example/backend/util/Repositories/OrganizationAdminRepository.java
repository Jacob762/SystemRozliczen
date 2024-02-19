package com.example.backend.util.Repositories;

import com.example.backend.util.Class.AdministratorOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationAdminRepository extends JpaRepository<AdministratorOrg, Integer> {
}
