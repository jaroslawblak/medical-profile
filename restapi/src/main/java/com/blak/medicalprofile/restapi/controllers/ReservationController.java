package com.blak.medicalprofile.restapi.controllers;

import com.blak.medicalprofile.dao.Calendar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {


    @GetMapping("/terms")
    public ResponseEntity<Calendar> getTerms() {
        return new ResponseEntity<>(new Calendar(), HttpStatus.OK);
    }
}
