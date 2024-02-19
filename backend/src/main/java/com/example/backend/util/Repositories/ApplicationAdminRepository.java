package com.example.backend.util.Repositories;

import com.example.backend.util.Class.AdministratorAPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationAdminRepository extends JpaRepository<AdministratorAPK, Integer> {
}
