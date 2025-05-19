package com.example.creditapp.mappers;

import com.example.creditapp.dtos.CreditDTO;
import com.example.creditapp.dtos.CreditImmobilierDTO;
import com.example.creditapp.dtos.CreditPersonnelDTO;
import com.example.creditapp.dtos.CreditProfessionnelDTO;
import com.example.creditapp.entities.Credit;
import com.example.creditapp.entities.CreditImmobilier;
import com.example.creditapp.entities.CreditPersonnel;
import com.example.creditapp.entities.CreditProfessionnel;
import com.example.creditapp.entities.Remboursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "remboursements", target = "remboursementIds", qualifiedByName = "remboursementsToRemboursementIds")
    CreditDTO toDTO(Credit credit);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "remboursements", target = "remboursementIds", qualifiedByName = "remboursementsToRemboursementIds")
    CreditPersonnelDTO toPersonnelDTO(CreditPersonnel creditPersonnel);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "remboursements", target = "remboursementIds", qualifiedByName = "remboursementsToRemboursementIds")
    CreditImmobilierDTO toImmobilierDTO(CreditImmobilier creditImmobilier);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "remboursements", target = "remboursementIds", qualifiedByName = "remboursementsToRemboursementIds")
    CreditProfessionnelDTO toProfessionnelDTO(CreditProfessionnel creditProfessionnel);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "remboursements", ignore = true)
    CreditPersonnel toPersonnelEntity(CreditPersonnelDTO creditPersonnelDTO);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "remboursements", ignore = true)
    CreditImmobilier toImmobilierEntity(CreditImmobilierDTO creditImmobilierDTO);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "remboursements", ignore = true)
    CreditProfessionnel toProfessionnelEntity(CreditProfessionnelDTO creditProfessionnelDTO);

    List<CreditDTO> toDTOList(List<Credit> credits);

    @Named("remboursementsToRemboursementIds")
    default List<Long> remboursementsToRemboursementIds(List<Remboursement> remboursements) {
        if (remboursements == null) {
            return null;
        }
        return remboursements.stream()
                .map(Remboursement::getId)
                .collect(Collectors.toList());
    }
}
