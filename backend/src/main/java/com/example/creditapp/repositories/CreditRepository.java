package com.example.creditapp.repositories;

import com.example.creditapp.entities.Credit;
import com.example.creditapp.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByCustomerId(Long customerId);
    List<Credit> findByStatut(StatutCredit statut);
}
