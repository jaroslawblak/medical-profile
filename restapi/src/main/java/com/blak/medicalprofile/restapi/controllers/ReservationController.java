package com.blak.medicalprofile.restapi.controllers;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.Timetable;
import com.blak.medicalprofile.services.MockService;
import com.blak.medicalprofile.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private MockService mockService;

    private TimetableService timetableService;

    public ReservationController(@Autowired TimetableService timetableService, @Autowired MockService mockService) {
        this.timetableService = timetableService;
        this.mockService = mockService;
    }

    @GetMapping("/mock/calendar")
    public ResponseEntity<Timetable> getMockedTimetables() {
        return new ResponseEntity<>(mockService.getMockedCalendar(), HttpStatus.OK);
    }

    @GetMapping("/calendar/doctors/{id}")
    public ResponseEntity<Timetable> getMockedTimetables(@PathVariable int id) {
        return new ResponseEntity<>(timetableService.getCalendarForDoctor(id), HttpStatus.OK);
    }

    @GetMapping("/mock/doctors")
    public ResponseEntity<List<Doctor>> getMockedDoctors() {
        return new ResponseEntity<>(mockService.getMockedDoctorList(), HttpStatus.OK);
    }
//    @GetMapping("/terms/free/{day}")
//    public ResponseEntity<List<LocalTime>> getFreeTermsForDoctor(@RequestBody Doctor doctor, @PathVariable int day) {
//        return new ResponseEntity<>(this.reservationService.getFreeTermsForDoctor(doctor, day), HttpStatus.OK);
//    }
}

