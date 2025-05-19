package com.example.creditapp.web;

import com.example.creditapp.dtos.CreditDTO;
import com.example.creditapp.dtos.CreditImmobilierDTO;
import com.example.creditapp.dtos.CreditPersonnelDTO;
import com.example.creditapp.dtos.CreditProfessionnelDTO;
import com.example.creditapp.enums.StatutCredit;
import com.example.creditapp.services.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
@Tag(name = "Credit API", description = "API for managing credits")
public class CreditController {
    
    private final CreditService creditService;
    
    @GetMapping
    @Operation(summary = "Get all credits", description = "Retrieves a list of all credits")
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        return ResponseEntity.ok(creditService.getAllCredits());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get credit by ID", description = "Retrieves a credit by its ID")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getCreditById(id));
    }
    
    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get credits by customer ID", description = "Retrieves all credits for a specific customer")
    public ResponseEntity<List<CreditDTO>> getCreditsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(creditService.getCreditsByCustomerId(customerId));
    }
    
    @GetMapping("/statut/{statut}")
    @Operation(summary = "Get credits by status", description = "Retrieves all credits with a specific status")
    public ResponseEntity<List<CreditDTO>> getCreditsByStatut(@PathVariable StatutCredit statut) {
        return ResponseEntity.ok(creditService.getCreditsByStatut(statut));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete credit", description = "Deletes a credit by its ID")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/statut")
    @Operation(summary = "Update credit status", description = "Updates the status of a credit")
    public ResponseEntity<CreditDTO> updateCreditStatus(@PathVariable Long id, @RequestParam StatutCredit statut) {
        return ResponseEntity.ok(creditService.updateCreditStatus(id, statut));
    }
    
    // Credit Personnel endpoints
    @PostMapping("/personnel")
    @Operation(summary = "Create personal credit", description = "Creates a new personal credit")
    public ResponseEntity<CreditPersonnelDTO> createCreditPersonnel(@RequestBody CreditPersonnelDTO creditPersonnelDTO) {
        return new ResponseEntity<>(creditService.saveCreditPersonnel(creditPersonnelDTO), HttpStatus.CREATED);
    }
    
    @PutMapping("/personnel/{id}")
    @Operation(summary = "Update personal credit", description = "Updates an existing personal credit")
    public ResponseEntity<CreditPersonnelDTO> updateCreditPersonnel(@PathVariable Long id, @RequestBody CreditPersonnelDTO creditPersonnelDTO) {
        return ResponseEntity.ok(creditService.updateCreditPersonnel(id, creditPersonnelDTO));
    }
    
    @GetMapping("/personnel/motif/{motif}")
    @Operation(summary = "Get personal credits by motif", description = "Retrieves personal credits by motif")
    public ResponseEntity<List<CreditPersonnelDTO>> getCreditPersonnelByMotif(@PathVariable String motif) {
        return ResponseEntity.ok(creditService.getCreditPersonnelByMotif(motif));
    }
    
    // Credit Immobilier endpoints
    @PostMapping("/immobilier")
    @Operation(summary = "Create real estate credit", description = "Creates a new real estate credit")
    public ResponseEntity<CreditImmobilierDTO> createCreditImmobilier(@RequestBody CreditImmobilierDTO creditImmobilierDTO) {
        return new ResponseEntity<>(creditService.saveCreditImmobilier(creditImmobilierDTO), HttpStatus.CREATED);
    }
    
    @PutMapping("/immobilier/{id}")
    @Operation(summary = "Update real estate credit", description = "Updates an existing real estate credit")
    public ResponseEntity<CreditImmobilierDTO> updateCreditImmobilier(@PathVariable Long id, @RequestBody CreditImmobilierDTO creditImmobilierDTO) {
        return ResponseEntity.ok(creditService.updateCreditImmobilier(id, creditImmobilierDTO));
    }
    
    // Credit Professionnel endpoints
    @PostMapping("/professionnel")
    @Operation(summary = "Create professional credit", description = "Creates a new professional credit")
    public ResponseEntity<CreditProfessionnelDTO> createCreditProfessionnel(@RequestBody CreditProfessionnelDTO creditProfessionnelDTO) {
        return new ResponseEntity<>(creditService.saveCreditProfessionnel(creditProfessionnelDTO), HttpStatus.CREATED);
    }
    
    @PutMapping("/professionnel/{id}")
    @Operation(summary = "Update professional credit", description = "Updates an existing professional credit")
    public ResponseEntity<CreditProfessionnelDTO> updateCreditProfessionnel(@PathVariable Long id, @RequestBody CreditProfessionnelDTO creditProfessionnelDTO) {
        return ResponseEntity.ok(creditService.updateCreditProfessionnel(id, creditProfessionnelDTO));
    }
    
    @GetMapping("/professionnel/raison-sociale/{raisonSociale}")
    @Operation(summary = "Get professional credits by raison sociale", description = "Retrieves professional credits by raison sociale")
    public ResponseEntity<List<CreditProfessionnelDTO>> getCreditProfessionnelByRaisonSociale(@PathVariable String raisonSociale) {
        return ResponseEntity.ok(creditService.getCreditProfessionnelByRaisonSociale(raisonSociale));
    }
}
