package com.example.creditapp.services;

import com.example.creditapp.dtos.CreditDTO;
import com.example.creditapp.dtos.CreditImmobilierDTO;
import com.example.creditapp.dtos.CreditPersonnelDTO;
import com.example.creditapp.dtos.CreditProfessionnelDTO;
import com.example.creditapp.enums.StatutCredit;

import java.util.List;

public interface CreditService {
    List<CreditDTO> getAllCredits();
    CreditDTO getCreditById(Long id);
    List<CreditDTO> getCreditsByCustomerId(Long customerId);
    List<CreditDTO> getCreditsByStatut(StatutCredit statut);
    void deleteCredit(Long id);
    
    CreditPersonnelDTO saveCreditPersonnel(CreditPersonnelDTO creditPersonnelDTO);
    CreditPersonnelDTO updateCreditPersonnel(Long id, CreditPersonnelDTO creditPersonnelDTO);
    List<CreditPersonnelDTO> getCreditPersonnelByMotif(String motif);
    
    CreditImmobilierDTO saveCreditImmobilier(CreditImmobilierDTO creditImmobilierDTO);
    CreditImmobilierDTO updateCreditImmobilier(Long id, CreditImmobilierDTO creditImmobilierDTO);
    
    CreditProfessionnelDTO saveCreditProfessionnel(CreditProfessionnelDTO creditProfessionnelDTO);
    CreditProfessionnelDTO updateCreditProfessionnel(Long id, CreditProfessionnelDTO creditProfessionnelDTO);
    List<CreditProfessionnelDTO> getCreditProfessionnelByRaisonSociale(String raisonSociale);
    
    CreditDTO updateCreditStatus(Long id, StatutCredit statut);
}
