package com.challenge.controller;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company/")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{companyId}")
    public ResponseEntity<Optional<Company>> findById(@PathVariable(value = "companyId") Long id){
        return !companyService.findById(id).isPresent() ?
                ResponseEntity.ok(companyService.findById(id))
                : ResponseEntity.notFound().build();

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{accelerationId}")
    public ResponseEntity<List<Company>> findByAccelerationId(@PathVariable(value = "accelerationId") Long accelerationId){
        return !companyService.findByAccelerationId(accelerationId).isEmpty() ?
                ResponseEntity.ok(companyService.findByAccelerationId(accelerationId))
                : ResponseEntity.notFound().build();

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{userId}")
    public ResponseEntity<List<Company>> findByUserId(@PathVariable(value = "userId") Long userId){
        return !companyService.findByUserId(userId).isEmpty() ?
                ResponseEntity.ok(companyService.findByUserId(userId))
                : ResponseEntity.notFound().build();

    }
}
