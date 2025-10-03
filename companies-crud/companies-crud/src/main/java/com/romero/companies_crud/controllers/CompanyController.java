package com.romero.companies_crud.controllers;


import com.romero.companies_crud.entities.Company;
import com.romero.companies_crud.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "company")
@Tag(name="Companies resource")

public class CompanyController {

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyService companyService;

    // Constructor for dependency injection
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Operation(summary="Give a name to the company")
    @GetMapping(path = "{name}")
    public ResponseEntity<Company> get(@PathVariable String name){
        log.info("GET: company{}", name);
        return ResponseEntity.ok(this.companyService.readByName(name));

    }

    @Operation(summary="Post a name to the company")
    @PostMapping
    public ResponseEntity <Company> post(@RequestBody Company company){
        log.info("POST: company{}", company.getName());
        return ResponseEntity.created(
                URI.create(this.companyService.create(company).getName()))
                .build();
    }

    @Operation(summary="Update the name from a company")
    @PutMapping(path = "{name}")
    public ResponseEntity<Company> put(@RequestBody Company company ,
                                       @PathVariable String name){
        log.info("PUT: company{}", name);
        return ResponseEntity.ok(this.companyService.update(company,name));

    }

    @Operation(summary="Delete a company")
    @DeleteMapping(path = "{name}")
    public ResponseEntity<?> delete(@PathVariable String name){
        log.info("DELETE: company{}", name);
        this.companyService.delete(name);
        return ResponseEntity.noContent().build();
    }

}
