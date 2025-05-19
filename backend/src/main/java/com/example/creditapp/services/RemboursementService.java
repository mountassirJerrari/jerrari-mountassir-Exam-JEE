package com.example.creditapp.services;

import com.example.creditapp.dtos.RemboursementDTO;
import com.example.creditapp.enums.TypeRemboursement;

import java.util.Date;
import java.util.List;

public interface RemboursementService {
    List<RemboursementDTO> getAllRemboursements();
    RemboursementDTO getRemboursementById(Long id);
    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);
    List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type);
    List<RemboursementDTO> getRemboursementsByDateRange(Date startDate, Date endDate);
    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO);
    RemboursementDTO updateRemboursement(Long id, RemboursementDTO remboursementDTO);
    void deleteRemboursement(Long id);
}
