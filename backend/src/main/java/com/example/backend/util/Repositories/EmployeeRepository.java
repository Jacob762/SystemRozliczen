package com.example.backend.util.Repositories;

import com.example.backend.util.Class.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Pracownik,Integer> {
}
