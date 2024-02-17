package com.example.backend.util.Repositories;

import com.example.backend.util.Class.Dokument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Dokument,Integer> {
}
