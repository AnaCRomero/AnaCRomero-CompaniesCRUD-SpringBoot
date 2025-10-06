package com.anaromero.report_ms.service;

import com.anaromero.report_ms.models.Company;
import com.anaromero.report_ms.repositories.CompaniesRepository;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{

    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final CompaniesRepository companiesRepository;

    // Constructor for dependency injection
    public ReportServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public String makeReport(String name) {
        try {
            log.info("Making report for company: " + name);
            log.info("Calling companies repository...");
            
            var companyOptional = this.companiesRepository.getByName(name);
            log.info("Repository call completed. Company found: " + companyOptional.isPresent());
            
            if (companyOptional.isPresent()) {
                String companyName = companyOptional.get().getName();
                log.info("Returning company name: " + companyName);
                return companyName;
            } else {
                log.warn("No company found with name: " + name);
                return "Company not found: " + name;
            }
        } catch (Exception e) {
            log.error("Error fetching company data for name: " + name, e);
            log.error("Exception type: " + e.getClass().getSimpleName());
            log.error("Exception message: " + e.getMessage());
            return "Error: Could not retrieve company data for " + name + ". Details: " + e.getMessage();
        }
    }

    @Override
    public String saveReport(String nameReport) {
        return null;
    }

    @Override
    public void deleteReport(String name) {

    }

    @Override
    public Company createCompany(Company company) {
        try {
            log.info("Creating company: " + company.getName());
            Company createdCompany = this.companiesRepository.createCompany(company);
            log.info("Company created successfully: " + createdCompany.getName());
            return createdCompany;
        } catch (Exception e) {
            log.error("Error creating company: " + company.getName(), e);
            throw new RuntimeException("Error creating company: " + e.getMessage());
        }
    }

    @Override
    public Company updateCompany(String name, Company company) {
        try {
            log.info("Updating company: " + name);
            Company updatedCompany = this.companiesRepository.updateCompany(name, company);
            log.info("Company updated successfully: " + updatedCompany.getName());
            return updatedCompany;
        } catch (Exception e) {
            log.error("Error updating company: " + name, e);
            throw new RuntimeException("Error updating company: " + e.getMessage());
        }
    }

    @Override
    public void deleteCompany(String name) {
        try {
            log.info("Deleting company: " + name);
            this.companiesRepository.deleteCompany(name);
            log.info("Company deleted successfully: " + name);
        } catch (Exception e) {
            log.error("Error deleting company: " + name, e);
            throw new RuntimeException("Error deleting company: " + e.getMessage());
        }
    }
}
