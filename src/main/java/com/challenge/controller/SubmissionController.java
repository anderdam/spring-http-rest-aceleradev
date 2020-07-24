package com.challenge.controller;

import com.challenge.entity.Submission;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = {"{challengeId}", "{accelerationId}"})
    public ResponseEntity<List<Submission>> findByChallengeIdAndAccelerationId(@PathVariable(value = "challengeId") Long challengeId,
                                                                               @PathVariable(value = "accelerationId") Long accelerationId){
        return !submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId).isEmpty() ?
                ResponseEntity.ok(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId))
                : ResponseEntity.notFound().build();

    }
}
