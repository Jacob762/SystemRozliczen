package com.example.backend.util.Repositories;

import com.example.backend.util.Class.Organizacja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organizacja,Integer> {
}
