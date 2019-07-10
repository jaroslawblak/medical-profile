package com.blak.medicalprofile.services;

import com.blak.medicalprofile.dao.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class MockService {

    public List<Doctor> getMockedDoctorList() {
        ArrayList<Doctor> doctors = new ArrayList<>();

        Doctor firstDoctor = new Doctor(DoctorType.CARDIOLOGIST);
        firstDoctor.firstName("Artur")
                .lastName("Jedrzejczyk")
                .birthday(LocalDate.of(1976, 7, 30))
                .pesel("453965432")
                .userKey("1121343232432");

        Doctor secondDoctor = new Doctor(DoctorType.DENTIST);
        secondDoctor.firstName("Jakub")
                .lastName("Blaszczykowski")
                .birthday(LocalDate.of(1996, 10, 2))
                .pesel("35437687")
                .userKey("987987755434");

        Doctor thirdDoctor = new Doctor(DoctorType.ORTHOPAEDIST);
        thirdDoctor.firstName("Artur")
                .lastName("Boruc")
                .birthday(LocalDate.of(1990, 12, 7))
                .pesel("76456544")
                .userKey("43243535334");

        Doctor fourthDoctor = new Doctor(DoctorType.SURGEON);
        fourthDoctor.firstName("Robert")
                .lastName("Lewandowski")
                .birthday(LocalDate.of(1982, 2, 21))
                .pesel("53223423")
                .userKey("4675765876");

        doctors.add(firstDoctor);
        doctors.add(secondDoctor);
        doctors.add(thirdDoctor);
        doctors.add(fourthDoctor);
        return doctors;
    }

    public Map<Doctor, Map<LocalDate, Set<Visit>>> mockBusyTermsForDoctors() {
        Reservation reservation = new Reservation();
        List<Doctor> doctors = getMockedDoctorList();
        if (Reservation.getDoctorTimetable().isEmpty()) {
            for (Doctor doctor : doctors) {
                Map<LocalDate, Set<Visit>> mockedDoctorTimetable = new TreeMap<>();
                Random random = new Random();
                for (int i = 0; i < 20; i++) {
                    LocalDate randomDay = LocalDate.now().withDayOfMonth(random.nextInt(31) + 1);
                    mockedDoctorTimetable.put(randomDay, mockAllTermsInDay(randomDay));
                    Reservation.getDoctorTimetable().put(doctor, mockedDoctorTimetable);
                }
            }
        }
        return Reservation.getDoctorTimetable();
    }

    private Set<Visit> mockAllTermsInDay(LocalDate date) {
        TreeSet<Visit> visits = new TreeSet<>();
        for (int k = 0; k < 8; k++) {
            visits.add(new Visit(date, LocalTime.of(10 + k, 0), null, VisitType.REGULAR));
        }
        return visits;
    }
}
