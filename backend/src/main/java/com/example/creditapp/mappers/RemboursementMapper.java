package com.example.creditapp.mappers;

import com.example.creditapp.dtos.RemboursementDTO;
import com.example.creditapp.entities.Remboursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RemboursementMapper {
    
    @Mapping(source = "credit.id", target = "creditId")
    RemboursementDTO toDTO(Remboursement remboursement);
    
    @Mapping(target = "credit", ignore = true)
    Remboursement toEntity(RemboursementDTO remboursementDTO);
    
    List<RemboursementDTO> toDTOList(List<Remboursement> remboursements);
}
