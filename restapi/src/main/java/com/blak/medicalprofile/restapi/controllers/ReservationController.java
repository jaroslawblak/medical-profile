package com.blak.medicalprofile.restapi.controllers;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.Timetable;
import com.blak.medicalprofile.dao.Visit;
import com.blak.medicalprofile.services.MockService;
import com.blak.medicalprofile.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(MockService.getMockedDoctorList(), HttpStatus.OK);
    }

    @PostMapping("/reservations/new")
    public ResponseEntity<Boolean> reserveTerm(@RequestBody Visit visit) {
        return new ResponseEntity<>(timetableService.addNewReservation(visit), HttpStatus.OK);
    }

    @DeleteMapping("/reservations/delete/{userKey}")
    public void deleteReservation(@PathVariable String userKey) {
        timetableService.deleteReservation(userKey);
    }

    @GetMapping("/reservations/details/{userKey}")
    public ResponseEntity<Visit> getVisitDetails(@PathVariable String userKey) {
        return new ResponseEntity<>(timetableService.getVisitDetails(userKey), HttpStatus.OK);
    }
}
