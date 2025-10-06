package com.anaromero.report_ms.service;

import com.anaromero.report_ms.models.Company;

public interface ReportService {

    String makeReport(String name);
    String saveReport(String nameReport);
    void deleteReport(String name);
    
    // New CRUD methods for companies
    Company createCompany(Company company);
    Company updateCompany(String name, Company company);
    void deleteCompany(String name);
}
