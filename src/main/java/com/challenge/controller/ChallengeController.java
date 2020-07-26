package com.challenge.controller;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Challenge>> findByAccelerationIdAndUserId(@RequestParam(value = "accelerationId") Long accelerationId,
                                                                         @RequestParam(value = "userId") Long userId){
        List<Challenge> challenges = challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
        return !challenges.isEmpty() ? ResponseEntity.ok(challenges) : ResponseEntity.notFound().build();
    }
}
