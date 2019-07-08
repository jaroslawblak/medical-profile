package com.blak.medicalprofile.restapi.controllers;

import com.blak.medicalprofile.dao.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {


    @GetMapping("/terms")
    public ResponseEntity<Reservation> getTerms() {
        return new ResponseEntity<>(new Reservation(), HttpStatus.OK);
    }
}
