package com.challenge.controller;

import com.challenge.dto.SubmissionDTO;
import com.challenge.entity.Submission;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("submission")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final SubmissionMapper submissionMapper;

    @Autowired
    public SubmissionController(SubmissionService submissionService, SubmissionMapper submissionMapper) {
        this.submissionService = submissionService;
        this.submissionMapper = submissionMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findByChallengeIdAndAccelerationId(
            @RequestParam(value = "challengeId") Long challengeId,
            @RequestParam(value = "accelerationId") Long accelerationId) {

        List<Submission> submissions = submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
        return submissions.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok().body(submissionMapper.map(submissions));
    }
}
