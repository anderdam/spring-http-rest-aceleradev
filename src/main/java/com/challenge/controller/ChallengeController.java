package com.challenge.controller;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge/")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = {"{accelerationId}", "{userId}"})
    public ResponseEntity<List<Challenge>> findByAccelerationIdAndUserId(@PathVariable(value = "accelerationId") Long accelerationId,
                                                                         @PathVariable(value = "userId") Long userId){
        return !challengeService.findByAccelerationIdAndUserId(accelerationId, userId).isEmpty() ?
                ResponseEntity.ok(challengeService.findByAccelerationIdAndUserId(accelerationId, userId))
                : ResponseEntity.notFound().build();
    }
}
