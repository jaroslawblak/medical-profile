package com.blak.medicalprofile.restapi.controllers;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.Reservation;
import com.blak.medicalprofile.services.MockService;
import com.blak.medicalprofile.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RestController
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(@Autowired ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/mock/timetables")
    public ResponseEntity<Reservation> getMockedTimetables() {
        return new ResponseEntity<>(new MockService().mockBusyTermsForDoctors(), HttpStatus.OK);
    }

    @GetMapping("/mock/doctors")
    public ResponseEntity<List<Doctor>> getMockedDoctors() {
        return new ResponseEntity<>(new MockService().getMockedDoctorList(), HttpStatus.OK);
    }
    @GetMapping("/terms/free/{day}")
    public ResponseEntity<List<LocalTime>> getFreeTermsForDoctor(@RequestBody Doctor doctor, @PathVariable int day) {
        return new ResponseEntity<>(this.reservationService.getFreeTermsForDoctor(doctor, day), HttpStatus.OK);
    }
}

