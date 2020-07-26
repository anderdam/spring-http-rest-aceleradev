package com.challenge.controller;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("candidate")
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;

    @Autowired
    public CandidateController(CandidateService candidateService, CandidateMapper candidateMapper) {
        this.candidateService = candidateService;
        this.candidateMapper = candidateMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable(value = "userId") Long userId,
                                                 @PathVariable(value = "companyId") Long companyId,
                                                 @PathVariable(value = "accelerationId") Long accelerationId){
        return ResponseEntity.ok()
                .body(candidateMapper.map(candidateService.findById(userId, companyId, accelerationId)
                        .orElse(new Candidate())));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Candidate>> findByCompanyId(@RequestParam(value = "companyId") Long companyId){
        List<Candidate> candidates = candidateService.findByCompanyId(companyId);
        return !candidates.isEmpty()? ResponseEntity.ok(candidateService.findByCompanyId(companyId))
                : ResponseEntity.notFound().build();
    }
}
