package com.example.creditapp.services.impl;

import com.example.creditapp.dtos.RemboursementDTO;
import com.example.creditapp.entities.Credit;
import com.example.creditapp.entities.Remboursement;
import com.example.creditapp.enums.TypeRemboursement;
import com.example.creditapp.exceptions.ResourceNotFoundException;
import com.example.creditapp.mappers.RemboursementMapper;
import com.example.creditapp.repositories.CreditRepository;
import com.example.creditapp.repositories.RemboursementRepository;
import com.example.creditapp.services.RemboursementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemboursementServiceImpl implements RemboursementService {
    
    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final RemboursementMapper remboursementMapper;
    
    @Override
    public List<RemboursementDTO> getAllRemboursements() {
        List<Remboursement> remboursements = remboursementRepository.findAll();
        return remboursementMapper.toDTOList(remboursements);
    }
    
    @Override
    public RemboursementDTO getRemboursementById(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Remboursement not found with id: " + id));
        return remboursementMapper.toDTO(remboursement);
    }
    
    @Override
    public List<RemboursementDTO> getRemboursementsByCreditId(Long creditId) {
        List<Remboursement> remboursements = remboursementRepository.findByCreditId(creditId);
        return remboursementMapper.toDTOList(remboursements);
    }
    
    @Override
    public List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type) {
        List<Remboursement> remboursements = remboursementRepository.findByType(type);
        return remboursementMapper.toDTOList(remboursements);
    }
    
    @Override
    public List<RemboursementDTO> getRemboursementsByDateRange(Date startDate, Date endDate) {
        List<Remboursement> remboursements = remboursementRepository.findByDateBetween(startDate, endDate);
        return remboursementMapper.toDTOList(remboursements);
    }
    
    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        
        // Set credit
        Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                .orElseThrow(() -> new ResourceNotFoundException("Credit not found with id: " + remboursementDTO.getCreditId()));
        remboursement.setCredit(credit);
        
        // Set default values if not provided
        if (remboursement.getDate() == null) {
            remboursement.setDate(new Date());
        }
        
        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDTO(savedRemboursement);
    }
    
    @Override
    public RemboursementDTO updateRemboursement(Long id, RemboursementDTO remboursementDTO) {
        Remboursement existingRemboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Remboursement not found with id: " + id));
        
        existingRemboursement.setDate(remboursementDTO.getDate());
        existingRemboursement.setMontant(remboursementDTO.getMontant());
        existingRemboursement.setType(remboursementDTO.getType());
        
        Remboursement updatedRemboursement = remboursementRepository.save(existingRemboursement);
        return remboursementMapper.toDTO(updatedRemboursement);
    }
    
    @Override
    public void deleteRemboursement(Long id) {
        if (!remboursementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Remboursement not found with id: " + id);
        }
        remboursementRepository.deleteById(id);
    }
}
