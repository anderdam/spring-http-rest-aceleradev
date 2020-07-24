package com.challenge.controller;

import com.challenge.entity.Candidate;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate/")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = {"{accelerationId}", "{userId}", "{companyId}"})
    public ResponseEntity<Optional<Candidate>> findById(@PathVariable(value = "userId") Long userId,
                                                        @PathVariable(value = "companyId") Long companyId,
                                                        @PathVariable(value = "accelerationId") Long accelerationId){
        return candidateService.findById(userId, companyId, accelerationId).isPresent() ?
                ResponseEntity.ok(candidateService.findById(userId, companyId, accelerationId))
                : ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{companyId}")
    public ResponseEntity<List<Candidate>> findByCompanyId(@PathVariable(value = "companyId") Long companyId){
        return !candidateService.findByCompanyId(companyId).isEmpty() ?
                ResponseEntity.ok(candidateService.findByCompanyId(companyId))
                : ResponseEntity.notFound().build();

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{accelerationId}")
    public ResponseEntity<List<Candidate>> findByAccelerationId(@PathVariable(value = "accelerationId") Long accelerationId){
        return !candidateService.findByAccelerationId(accelerationId).isEmpty() ?
                ResponseEntity.ok(candidateService.findByCompanyId(accelerationId))
                : ResponseEntity.notFound().build();
    }
}
