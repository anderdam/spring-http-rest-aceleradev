package com.challenge.controller;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("company")
public class CompanyController {


    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> findById(@PathVariable Long companyId) {
        Optional<Company> company = companyService.findById(companyId);
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAllByAccelerationIdOrUserId(@RequestParam(required = false) Long accelerationId,
                                                                         @RequestParam(required = false) Long userId) {
        List<Company> companies = null;

        if (accelerationId == null || userId != null) {
            if (accelerationId == null && userId != null) {
                companies = companyService.findByUserId(userId);
            }
        } else {
            companies = companyService.findByAccelerationId(accelerationId);
        }

        assert companies != null;
        return ResponseEntity.ok(companies);
    }

}