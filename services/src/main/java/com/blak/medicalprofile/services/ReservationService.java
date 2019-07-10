package com.blak.medicalprofile.services;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.Reservation;
import com.blak.medicalprofile.dao.Visit;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private Reservation reservation;

    public ReservationService() {
        this.reservation = new Reservation();
    }

    public List<LocalTime> getFreeTermsForDoctor(Doctor doctor, int dayOfMonth){
        Map<Doctor, Map<LocalDate, Set<Visit>>> doctorTimetable = Reservation.getDoctorTimetable();
        Set<Visit> visits = doctorTimetable.get(doctor).get(LocalDate.now().withDayOfMonth(dayOfMonth));
        Set<LocalTime> visitsTime = reservation.getAllAvailableTerms();
        return visits.stream().filter(visit -> !visitsTime.contains(visit.getTime())).map(Visit::getTime).collect(Collectors.toList());
    }
}
