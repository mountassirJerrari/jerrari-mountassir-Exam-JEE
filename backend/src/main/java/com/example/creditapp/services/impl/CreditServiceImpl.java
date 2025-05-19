package com.example.creditapp.services.impl;

import com.example.creditapp.dtos.CreditDTO;
import com.example.creditapp.dtos.CreditImmobilierDTO;
import com.example.creditapp.dtos.CreditPersonnelDTO;
import com.example.creditapp.dtos.CreditProfessionnelDTO;
import com.example.creditapp.entities.*;
import com.example.creditapp.enums.StatutCredit;
import com.example.creditapp.exceptions.ResourceNotFoundException;
import com.example.creditapp.mappers.CreditMapper;
import com.example.creditapp.repositories.*;
import com.example.creditapp.services.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    
    private final CreditRepository creditRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final CustomerRepository customerRepository;
    private final CreditMapper creditMapper;
    
    @Override
    public List<CreditDTO> getAllCredits() {
        List<Credit> credits = creditRepository.findAll();
        return creditMapper.toDTOList(credits);
    }
    
    @Override
    public CreditDTO getCreditById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit not found with id: " + id));
        return creditMapper.toDTO(credit);
    }
    
    @Override
    public List<CreditDTO> getCreditsByCustomerId(Long customerId) {
        List<Credit> credits = creditRepository.findByCustomerId(customerId);
        return creditMapper.toDTOList(credits);
    }
    
    @Override
    public List<CreditDTO> getCreditsByStatut(StatutCredit statut) {
        List<Credit> credits = creditRepository.findByStatut(statut);
        return creditMapper.toDTOList(credits);
    }
    
    @Override
    public void deleteCredit(Long id) {
        if (!creditRepository.existsById(id)) {
            throw new ResourceNotFoundException("Credit not found with id: " + id);
        }
        creditRepository.deleteById(id);
    }
    
    @Override
    public CreditPersonnelDTO saveCreditPersonnel(CreditPersonnelDTO creditPersonnelDTO) {
        CreditPersonnel creditPersonnel = creditMapper.toPersonnelEntity(creditPersonnelDTO);
        
        // Set customer
        Customer customer = customerRepository.findById(creditPersonnelDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + creditPersonnelDTO.getCustomerId()));
        creditPersonnel.setCustomer(customer);
        
        // Set default values if not provided
        if (creditPersonnel.getDateDemande() == null) {
            creditPersonnel.setDateDemande(new Date());
        }
        if (creditPersonnel.getStatut() == null) {
            creditPersonnel.setStatut(StatutCredit.EN_COURS);
        }
        
        CreditPersonnel savedCreditPersonnel = creditPersonnelRepository.save(creditPersonnel);
        return creditMapper.toPersonnelDTO(savedCreditPersonnel);
    }
    
    @Override
    public CreditPersonnelDTO updateCreditPersonnel(Long id, CreditPersonnelDTO creditPersonnelDTO) {
        CreditPersonnel existingCredit = creditPersonnelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Personnel not found with id: " + id));
        
        existingCredit.setMontant(creditPersonnelDTO.getMontant());
        existingCredit.setDureeRemboursement(creditPersonnelDTO.getDureeRemboursement());
        existingCredit.setTauxInteret(creditPersonnelDTO.getTauxInteret());
        existingCredit.setMotif(creditPersonnelDTO.getMotif());
        
        CreditPersonnel updatedCredit = creditPersonnelRepository.save(existingCredit);
        return creditMapper.toPersonnelDTO(updatedCredit);
    }
    
    @Override
    public List<CreditPersonnelDTO> getCreditPersonnelByMotif(String motif) {
        List<CreditPersonnel> credits = creditPersonnelRepository.findByMotifContaining(motif);
        return credits.stream()
                .map(creditMapper::toPersonnelDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public CreditImmobilierDTO saveCreditImmobilier(CreditImmobilierDTO creditImmobilierDTO) {
        CreditImmobilier creditImmobilier = creditMapper.toImmobilierEntity(creditImmobilierDTO);
        
        // Set customer
        Customer customer = customerRepository.findById(creditImmobilierDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + creditImmobilierDTO.getCustomerId()));
        creditImmobilier.setCustomer(customer);
        
        // Set default values if not provided
        if (creditImmobilier.getDateDemande() == null) {
            creditImmobilier.setDateDemande(new Date());
        }
        if (creditImmobilier.getStatut() == null) {
            creditImmobilier.setStatut(StatutCredit.EN_COURS);
        }
        
        CreditImmobilier savedCreditImmobilier = creditImmobilierRepository.save(creditImmobilier);
        return creditMapper.toImmobilierDTO(savedCreditImmobilier);
    }
    
    @Override
    public CreditImmobilierDTO updateCreditImmobilier(Long id, CreditImmobilierDTO creditImmobilierDTO) {
        CreditImmobilier existingCredit = creditImmobilierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Immobilier not found with id: " + id));
        
        existingCredit.setMontant(creditImmobilierDTO.getMontant());
        existingCredit.setDureeRemboursement(creditImmobilierDTO.getDureeRemboursement());
        existingCredit.setTauxInteret(creditImmobilierDTO.getTauxInteret());
        existingCredit.setTypeBien(creditImmobilierDTO.getTypeBien());
        
        CreditImmobilier updatedCredit = creditImmobilierRepository.save(existingCredit);
        return creditMapper.toImmobilierDTO(updatedCredit);
    }
    
    @Override
    public CreditProfessionnelDTO saveCreditProfessionnel(CreditProfessionnelDTO creditProfessionnelDTO) {
        CreditProfessionnel creditProfessionnel = creditMapper.toProfessionnelEntity(creditProfessionnelDTO);
        
        // Set customer
        Customer customer = customerRepository.findById(creditProfessionnelDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + creditProfessionnelDTO.getCustomerId()));
        creditProfessionnel.setCustomer(customer);
        
        // Set default values if not provided
        if (creditProfessionnel.getDateDemande() == null) {
            creditProfessionnel.setDateDemande(new Date());
        }
        if (creditProfessionnel.getStatut() == null) {
            creditProfessionnel.setStatut(StatutCredit.EN_COURS);
        }
        
        CreditProfessionnel savedCreditProfessionnel = creditProfessionnelRepository.save(creditProfessionnel);
        return creditMapper.toProfessionnelDTO(savedCreditProfessionnel);
    }
    
    @Override
    public CreditProfessionnelDTO updateCreditProfessionnel(Long id, CreditProfessionnelDTO creditProfessionnelDTO) {
        CreditProfessionnel existingCredit = creditProfessionnelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Professionnel not found with id: " + id));
        
        existingCredit.setMontant(creditProfessionnelDTO.getMontant());
        existingCredit.setDureeRemboursement(creditProfessionnelDTO.getDureeRemboursement());
        existingCredit.setTauxInteret(creditProfessionnelDTO.getTauxInteret());
        existingCredit.setMotif(creditProfessionnelDTO.getMotif());
        existingCredit.setRaisonSociale(creditProfessionnelDTO.getRaisonSociale());
        
        CreditProfessionnel updatedCredit = creditProfessionnelRepository.save(existingCredit);
        return creditMapper.toProfessionnelDTO(updatedCredit);
    }
    
    @Override
    public List<CreditProfessionnelDTO> getCreditProfessionnelByRaisonSociale(String raisonSociale) {
        List<CreditProfessionnel> credits = creditProfessionnelRepository.findByRaisonSocialeContaining(raisonSociale);
        return credits.stream()
                .map(creditMapper::toProfessionnelDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public CreditDTO updateCreditStatus(Long id, StatutCredit statut) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit not found with id: " + id));
        
        credit.setStatut(statut);
        
        // If status is ACCEPTE, set acceptance date
        if (statut == StatutCredit.ACCEPTE) {
            credit.setDateAcceptation(new Date());
        }
        
        Credit updatedCredit = creditRepository.save(credit);
        return creditMapper.toDTO(updatedCredit);
    }
}
