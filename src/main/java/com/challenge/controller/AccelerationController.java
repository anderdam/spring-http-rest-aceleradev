package com.challenge.controller;

import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acceleration/")
public class AccelerationController {

    private final AccelerationService accelerationService;

    @Autowired
    public AccelerationController(AccelerationService accelerationService) {
        this.accelerationService = accelerationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{accelerationId}")
    public ResponseEntity<Optional<Acceleration>> findById(@PathVariable(value = "accelerationId") Long id){
        return accelerationService.findById(id).isPresent() ?
                ResponseEntity.ok(accelerationService.findById(id))
                : ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{companyId}")
    public ResponseEntity<List<Acceleration>> findByCompanyId(@PathVariable(value = "companyId") Long companyId){
        return accelerationService.findByCompanyId(companyId).isEmpty() ?
                ResponseEntity.ok(accelerationService.findByCompanyId(companyId))
                : ResponseEntity.notFound().build();
    }
}
