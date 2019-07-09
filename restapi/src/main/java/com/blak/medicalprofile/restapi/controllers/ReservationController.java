package com.blak.medicalprofile.restapi.controllers;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.Reservation;
import com.blak.medicalprofile.services.MockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    @GetMapping("/mock/timetables")
    public ResponseEntity<Reservation> getMockedTimetables() {
        return new ResponseEntity<>(new MockService().mockBusyTermsForDoctors(), HttpStatus.OK);
    }

    @GetMapping("/mock/doctors")
    public ResponseEntity<List<Doctor>> getMockedDoctors() {
        return new ResponseEntity<>(new MockService().getMockedDoctorList(), HttpStatus.OK);
    }
}

