package com.example.backend.util.Repositories;

import com.example.backend.util.Class.Ksiegowy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountantRepository extends JpaRepository<Ksiegowy,Integer> {
}
