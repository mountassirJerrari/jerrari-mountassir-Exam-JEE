package com.example.creditapp.repositories;

import com.example.creditapp.entities.CreditPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditPersonnelRepository extends JpaRepository<CreditPersonnel, Long> {
    List<CreditPersonnel> findByCustomerId(Long customerId);
    List<CreditPersonnel> findByMotifContaining(String motif);
}
