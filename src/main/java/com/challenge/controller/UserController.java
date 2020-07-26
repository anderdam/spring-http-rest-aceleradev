package com.challenge.controller;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> findById(@PathVariable(value = "userId") Long userId) {
        Optional<User> user = userService.findById(userId);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<User>> findByAccelerationNameOrCompanyId(@RequestParam(required = false) String accelerationName,
                                                                        @RequestParam(required = false) Long companyId) {
        List<User> users = null;

        if (accelerationName == null || companyId != null) {
            if (accelerationName == null && companyId != null) {
                users = userService.findByCompanyId(companyId);
            }
        } else {
            users = userService.findByAccelerationName(accelerationName);
        }

        assert users != null;
        return ResponseEntity.ok(users);
    }
}
