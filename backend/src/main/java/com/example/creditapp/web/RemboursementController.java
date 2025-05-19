package com.example.creditapp.web;

import com.example.creditapp.dtos.RemboursementDTO;
import com.example.creditapp.enums.TypeRemboursement;
import com.example.creditapp.services.RemboursementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@RequiredArgsConstructor
@Tag(name = "Remboursement API", description = "API for managing remboursements")
public class RemboursementController {
    
    private final RemboursementService remboursementService;
    
    @GetMapping
    @Operation(summary = "Get all remboursements", description = "Retrieves a list of all remboursements")
    public ResponseEntity<List<RemboursementDTO>> getAllRemboursements() {
        return ResponseEntity.ok(remboursementService.getAllRemboursements());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get remboursement by ID", description = "Retrieves a remboursement by its ID")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        return ResponseEntity.ok(remboursementService.getRemboursementById(id));
    }
    
    @GetMapping("/credit/{creditId}")
    @Operation(summary = "Get remboursements by credit ID", description = "Retrieves all remboursements for a specific credit")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByCreditId(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.getRemboursementsByCreditId(creditId));
    }
    
    @GetMapping("/type/{type}")
    @Operation(summary = "Get remboursements by type", description = "Retrieves all remboursements of a specific type")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByType(@PathVariable TypeRemboursement type) {
        return ResponseEntity.ok(remboursementService.getRemboursementsByType(type));
    }
    
    @GetMapping("/date-range")
    @Operation(summary = "Get remboursements by date range", description = "Retrieves all remboursements within a date range")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return ResponseEntity.ok(remboursementService.getRemboursementsByDateRange(startDate, endDate));
    }
    
    @PostMapping
    @Operation(summary = "Create remboursement", description = "Creates a new remboursement")
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return new ResponseEntity<>(remboursementService.saveRemboursement(remboursementDTO), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update remboursement", description = "Updates an existing remboursement")
    public ResponseEntity<RemboursementDTO> updateRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(remboursementService.updateRemboursement(id, remboursementDTO));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete remboursement", description = "Deletes a remboursement by its ID")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }
}
