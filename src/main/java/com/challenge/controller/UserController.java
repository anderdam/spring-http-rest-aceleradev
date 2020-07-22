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
@RequestMapping("/user/")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "{userId}")
    public ResponseEntity<Optional<User>> findById(@PathVariable(value = "userId") Long id) {
        ResponseEntity<Optional<User>> result;
        if (userService.findById(id).isPresent()) {
            result = ResponseEntity.ok(userService.findById(id));

        } else {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/accelerationName")
    public ResponseEntity<List<User>> findByAccelerationName(String name){
        if (userService.findByAccelerationName(name).isEmpty()){
            return ResponseEntity.ok(userService.findByAccelerationName(name));
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/companyId")
    public ResponseEntity<List<User>> findByCompanyId(Long companyId){
        if (userService.findByCompanyId(companyId).isEmpty()){
            return ResponseEntity.ok(userService.findByCompanyId(companyId));
        }
        return ResponseEntity.notFound().build();
    }

}
