package com.example.creditapp.mappers;

import com.example.creditapp.dtos.CreditDTO;
import com.example.creditapp.dtos.CreditImmobilierDTO;
import com.example.creditapp.dtos.CreditPersonnelDTO;
import com.example.creditapp.dtos.CreditProfessionnelDTO;
import com.example.creditapp.entities.Credit;
import com.example.creditapp.entities.CreditImmobilier;
import com.example.creditapp.entities.CreditPersonnel;
import com.example.creditapp.entities.CreditProfessionnel;
import com.example.creditapp.entities.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T11:33:30+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Ubuntu)"
)
@Component
public class CreditMapperImpl implements CreditMapper {

    @Override
    public CreditDTO toDTO(Credit credit) {
        if ( credit == null ) {
            return null;
        }

        CreditDTO creditDTO = new CreditDTO();

        creditDTO.setCustomerId( creditCustomerId( credit ) );
        creditDTO.setRemboursementIds( remboursementsToRemboursementIds( credit.getRemboursements() ) );
        creditDTO.setId( credit.getId() );
        creditDTO.setDateDemande( credit.getDateDemande() );
        creditDTO.setStatut( credit.getStatut() );
        creditDTO.setDateAcceptation( credit.getDateAcceptation() );
        creditDTO.setMontant( credit.getMontant() );
        creditDTO.setDureeRemboursement( credit.getDureeRemboursement() );
        creditDTO.setTauxInteret( credit.getTauxInteret() );

        return creditDTO;
    }

    @Override
    public CreditPersonnelDTO toPersonnelDTO(CreditPersonnel creditPersonnel) {
        if ( creditPersonnel == null ) {
            return null;
        }

        CreditPersonnelDTO creditPersonnelDTO = new CreditPersonnelDTO();

        creditPersonnelDTO.setCustomerId( creditPersonnelCustomerId( creditPersonnel ) );
        creditPersonnelDTO.setRemboursementIds( remboursementsToRemboursementIds( creditPersonnel.getRemboursements() ) );
        creditPersonnelDTO.setId( creditPersonnel.getId() );
        creditPersonnelDTO.setDateDemande( creditPersonnel.getDateDemande() );
        creditPersonnelDTO.setStatut( creditPersonnel.getStatut() );
        creditPersonnelDTO.setDateAcceptation( creditPersonnel.getDateAcceptation() );
        creditPersonnelDTO.setMontant( creditPersonnel.getMontant() );
        creditPersonnelDTO.setDureeRemboursement( creditPersonnel.getDureeRemboursement() );
        creditPersonnelDTO.setTauxInteret( creditPersonnel.getTauxInteret() );
        creditPersonnelDTO.setMotif( creditPersonnel.getMotif() );

        return creditPersonnelDTO;
    }

    @Override
    public CreditImmobilierDTO toImmobilierDTO(CreditImmobilier creditImmobilier) {
        if ( creditImmobilier == null ) {
            return null;
        }

        CreditImmobilierDTO creditImmobilierDTO = new CreditImmobilierDTO();

        creditImmobilierDTO.setCustomerId( creditImmobilierCustomerId( creditImmobilier ) );
        creditImmobilierDTO.setRemboursementIds( remboursementsToRemboursementIds( creditImmobilier.getRemboursements() ) );
        creditImmobilierDTO.setId( creditImmobilier.getId() );
        creditImmobilierDTO.setDateDemande( creditImmobilier.getDateDemande() );
        creditImmobilierDTO.setStatut( creditImmobilier.getStatut() );
        creditImmobilierDTO.setDateAcceptation( creditImmobilier.getDateAcceptation() );
        creditImmobilierDTO.setMontant( creditImmobilier.getMontant() );
        creditImmobilierDTO.setDureeRemboursement( creditImmobilier.getDureeRemboursement() );
        creditImmobilierDTO.setTauxInteret( creditImmobilier.getTauxInteret() );
        creditImmobilierDTO.setTypeBien( creditImmobilier.getTypeBien() );

        return creditImmobilierDTO;
    }

    @Override
    public CreditProfessionnelDTO toProfessionnelDTO(CreditProfessionnel creditProfessionnel) {
        if ( creditProfessionnel == null ) {
            return null;
        }

        CreditProfessionnelDTO creditProfessionnelDTO = new CreditProfessionnelDTO();

        creditProfessionnelDTO.setCustomerId( creditProfessionnelCustomerId( creditProfessionnel ) );
        creditProfessionnelDTO.setRemboursementIds( remboursementsToRemboursementIds( creditProfessionnel.getRemboursements() ) );
        creditProfessionnelDTO.setId( creditProfessionnel.getId() );
        creditProfessionnelDTO.setDateDemande( creditProfessionnel.getDateDemande() );
        creditProfessionnelDTO.setStatut( creditProfessionnel.getStatut() );
        creditProfessionnelDTO.setDateAcceptation( creditProfessionnel.getDateAcceptation() );
        creditProfessionnelDTO.setMontant( creditProfessionnel.getMontant() );
        creditProfessionnelDTO.setDureeRemboursement( creditProfessionnel.getDureeRemboursement() );
        creditProfessionnelDTO.setTauxInteret( creditProfessionnel.getTauxInteret() );
        creditProfessionnelDTO.setMotif( creditProfessionnel.getMotif() );
        creditProfessionnelDTO.setRaisonSociale( creditProfessionnel.getRaisonSociale() );

        return creditProfessionnelDTO;
    }

    @Override
    public CreditPersonnel toPersonnelEntity(CreditPersonnelDTO creditPersonnelDTO) {
        if ( creditPersonnelDTO == null ) {
            return null;
        }

        CreditPersonnel creditPersonnel = new CreditPersonnel();

        creditPersonnel.setId( creditPersonnelDTO.getId() );
        creditPersonnel.setDateDemande( creditPersonnelDTO.getDateDemande() );
        creditPersonnel.setStatut( creditPersonnelDTO.getStatut() );
        creditPersonnel.setDateAcceptation( creditPersonnelDTO.getDateAcceptation() );
        creditPersonnel.setMontant( creditPersonnelDTO.getMontant() );
        creditPersonnel.setDureeRemboursement( creditPersonnelDTO.getDureeRemboursement() );
        creditPersonnel.setTauxInteret( creditPersonnelDTO.getTauxInteret() );
        creditPersonnel.setMotif( creditPersonnelDTO.getMotif() );

        return creditPersonnel;
    }

    @Override
    public CreditImmobilier toImmobilierEntity(CreditImmobilierDTO creditImmobilierDTO) {
        if ( creditImmobilierDTO == null ) {
            return null;
        }

        CreditImmobilier creditImmobilier = new CreditImmobilier();

        creditImmobilier.setId( creditImmobilierDTO.getId() );
        creditImmobilier.setDateDemande( creditImmobilierDTO.getDateDemande() );
        creditImmobilier.setStatut( creditImmobilierDTO.getStatut() );
        creditImmobilier.setDateAcceptation( creditImmobilierDTO.getDateAcceptation() );
        creditImmobilier.setMontant( creditImmobilierDTO.getMontant() );
        creditImmobilier.setDureeRemboursement( creditImmobilierDTO.getDureeRemboursement() );
        creditImmobilier.setTauxInteret( creditImmobilierDTO.getTauxInteret() );
        creditImmobilier.setTypeBien( creditImmobilierDTO.getTypeBien() );

        return creditImmobilier;
    }

    @Override
    public CreditProfessionnel toProfessionnelEntity(CreditProfessionnelDTO creditProfessionnelDTO) {
        if ( creditProfessionnelDTO == null ) {
            return null;
        }

        CreditProfessionnel creditProfessionnel = new CreditProfessionnel();

        creditProfessionnel.setId( creditProfessionnelDTO.getId() );
        creditProfessionnel.setDateDemande( creditProfessionnelDTO.getDateDemande() );
        creditProfessionnel.setStatut( creditProfessionnelDTO.getStatut() );
        creditProfessionnel.setDateAcceptation( creditProfessionnelDTO.getDateAcceptation() );
        creditProfessionnel.setMontant( creditProfessionnelDTO.getMontant() );
        creditProfessionnel.setDureeRemboursement( creditProfessionnelDTO.getDureeRemboursement() );
        creditProfessionnel.setTauxInteret( creditProfessionnelDTO.getTauxInteret() );
        creditProfessionnel.setMotif( creditProfessionnelDTO.getMotif() );
        creditProfessionnel.setRaisonSociale( creditProfessionnelDTO.getRaisonSociale() );

        return creditProfessionnel;
    }

    @Override
    public List<CreditDTO> toDTOList(List<Credit> credits) {
        if ( credits == null ) {
            return null;
        }

        List<CreditDTO> list = new ArrayList<CreditDTO>( credits.size() );
        for ( Credit credit : credits ) {
            list.add( toDTO( credit ) );
        }

        return list;
    }

    private Long creditCustomerId(Credit credit) {
        if ( credit == null ) {
            return null;
        }
        Customer customer = credit.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long creditPersonnelCustomerId(CreditPersonnel creditPersonnel) {
        if ( creditPersonnel == null ) {
            return null;
        }
        Customer customer = creditPersonnel.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long creditImmobilierCustomerId(CreditImmobilier creditImmobilier) {
        if ( creditImmobilier == null ) {
            return null;
        }
        Customer customer = creditImmobilier.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long creditProfessionnelCustomerId(CreditProfessionnel creditProfessionnel) {
        if ( creditProfessionnel == null ) {
            return null;
        }
        Customer customer = creditProfessionnel.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
