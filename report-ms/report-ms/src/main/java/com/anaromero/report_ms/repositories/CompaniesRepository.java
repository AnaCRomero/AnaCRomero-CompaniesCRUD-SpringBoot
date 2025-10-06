package com.anaromero.report_ms.repositories;

import com.anaromero.report_ms.config.FeignConfig;
import com.anaromero.report_ms.models.Company;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name="companies-crud", configuration = FeignConfig.class)
public interface CompaniesRepository {

    @GetMapping(path = "/companies-crud/company/{name}")
    Optional<Company> getByName(@PathVariable String name);

    @PostMapping(path = "/companies-crud/company")
    Company createCompany(@RequestBody Company company);

    @PutMapping(path = "/companies-crud/company/{name}")
    Company updateCompany(@PathVariable String name, @RequestBody Company company);

    @DeleteMapping(path = "/companies-crud/company/{name}")
    void deleteCompany(@PathVariable String name);
}
