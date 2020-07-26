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
@RequestMapping("/acceleration")
public class AccelerationController {

    private final AccelerationService accelerationService;

    @Autowired
    public AccelerationController(AccelerationService accelerationService) {
        this.accelerationService = accelerationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{accelerationId}")
    public ResponseEntity<Acceleration> findById(@PathVariable(value = "accelerationId") Long accelerationId){
        Optional<Acceleration> acceleration = accelerationService.findById(accelerationId);
        return acceleration.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Acceleration>> findByCompanyId(@RequestParam Long companyId) {
        List<Acceleration> accelerations = accelerationService.findByCompanyId(companyId);
        return !accelerations.isEmpty() ? ResponseEntity.ok(accelerations) : ResponseEntity.notFound().build();
    }
}
