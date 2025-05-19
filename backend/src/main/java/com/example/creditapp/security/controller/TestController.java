package com.example.creditapp.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test API", description = "API for testing security")
public class TestController {

    @GetMapping("/public")
    @Operation(summary = "Public endpoint", description = "Accessible to all users")
    public String publicEndpoint() {
        return "Public content";
    }

    @GetMapping("/client")
    @Operation(summary = "Client endpoint", description = "Accessible to clients only")
    @PreAuthorize("hasRole('CLIENT')")
    public String clientEndpoint() {
        return "Client content";
    }

    @GetMapping("/employe")
    @Operation(summary = "Employee endpoint", description = "Accessible to employees only")
    @PreAuthorize("hasRole('EMPLOYE')")
    public String employeEndpoint() {
        return "Employee content";
    }

    @GetMapping("/admin")
    @Operation(summary = "Admin endpoint", description = "Accessible to admins only")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "Admin content";
    }
}
