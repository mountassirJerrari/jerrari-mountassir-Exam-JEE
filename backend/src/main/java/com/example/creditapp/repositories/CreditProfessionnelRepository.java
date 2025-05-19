package com.example.creditapp.repositories;

import com.example.creditapp.entities.CreditProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Long> {
    List<CreditProfessionnel> findByCustomerId(Long customerId);
    List<CreditProfessionnel> findByRaisonSocialeContaining(String raisonSociale);
    List<CreditProfessionnel> findByMotifContaining(String motif);
}
