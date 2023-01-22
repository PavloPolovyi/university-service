package com.example.universityservice.repository;

import com.example.universityservice.model.Lector;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findByNameIgnoreCaseContaining(String pattern);
}
