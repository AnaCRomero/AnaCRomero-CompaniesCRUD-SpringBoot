package com.anaromero.report_ms.controllers;

import com.anaromero.report_ms.models.Company;
import com.anaromero.report_ms.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="report")
public class ReportController {

    private final ReportService reportService;

    // Constructor for dependency injection
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(path = "{name}")
    public ResponseEntity<Map<String, String>> getReport(@PathVariable String name){
        var response = Map.of("report", this.reportService.makeReport(name));
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "company")
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        Company createdCompany = this.reportService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @PutMapping(path = "company/{name}")
    public ResponseEntity<Company> updateCompany(@PathVariable String name, @RequestBody Company company){
        Company updatedCompany = this.reportService.updateCompany(name, company);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping(path = "company/{name}")
    public ResponseEntity<Map<String, String>> deleteCompany(@PathVariable String name){
        this.reportService.deleteCompany(name);
        var response = Map.of("message", "Company " + name + " deleted successfully");
        return ResponseEntity.ok(response);
    }

}
