package com.example.creditapp.mappers;

import com.example.creditapp.dtos.RemboursementDTO;
import com.example.creditapp.entities.Credit;
import com.example.creditapp.entities.Remboursement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T09:18:09+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class RemboursementMapperImpl implements RemboursementMapper {

    @Override
    public RemboursementDTO toDTO(Remboursement remboursement) {
        if ( remboursement == null ) {
            return null;
        }

        RemboursementDTO remboursementDTO = new RemboursementDTO();

        remboursementDTO.setCreditId( remboursementCreditId( remboursement ) );
        remboursementDTO.setId( remboursement.getId() );
        remboursementDTO.setDate( remboursement.getDate() );
        remboursementDTO.setMontant( remboursement.getMontant() );
        remboursementDTO.setType( remboursement.getType() );

        return remboursementDTO;
    }

    @Override
    public Remboursement toEntity(RemboursementDTO remboursementDTO) {
        if ( remboursementDTO == null ) {
            return null;
        }

        Remboursement remboursement = new Remboursement();

        remboursement.setId( remboursementDTO.getId() );
        remboursement.setDate( remboursementDTO.getDate() );
        remboursement.setMontant( remboursementDTO.getMontant() );
        remboursement.setType( remboursementDTO.getType() );

        return remboursement;
    }

    @Override
    public List<RemboursementDTO> toDTOList(List<Remboursement> remboursements) {
        if ( remboursements == null ) {
            return null;
        }

        List<RemboursementDTO> list = new ArrayList<RemboursementDTO>( remboursements.size() );
        for ( Remboursement remboursement : remboursements ) {
            list.add( toDTO( remboursement ) );
        }

        return list;
    }

    private Long remboursementCreditId(Remboursement remboursement) {
        if ( remboursement == null ) {
            return null;
        }
        Credit credit = remboursement.getCredit();
        if ( credit == null ) {
            return null;
        }
        Long id = credit.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
