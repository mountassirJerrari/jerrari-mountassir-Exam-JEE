package com.example.creditapp.repositories;

import com.example.creditapp.entities.CreditImmobilier;
import com.example.creditapp.enums.TypeBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
    List<CreditImmobilier> findByCustomerId(Long customerId);
    List<CreditImmobilier> findByTypeBien(TypeBien typeBien);
}
