package com.blak.medicalprofile.restapi.controllers;

import com.blak.medicalprofile.dao.Patient;
import com.blak.medicalprofile.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/keys/generate")
    public ResponseEntity<Patient> generateUserKey(@RequestBody Patient patient) {
        return new ResponseEntity<>(userService.generateUserKey(patient), HttpStatus.OK);
    }
}
