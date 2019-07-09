package com.blak.medicalprofile.services;

import com.blak.medicalprofile.dao.Doctor;
import com.blak.medicalprofile.dao.DoctorType;
import com.blak.medicalprofile.dao.Reservation;
import com.blak.medicalprofile.dao.Visit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    public Reservation mockBusyTermsForDoctors() {
        Reservation reservation = new Reservation();
        List<Doctor> doctors = getMockedDoctorList();
        if (Reservation.getDoctorTimetable().isEmpty()) {
            for (Doctor doctor : doctors) {
                Map<LocalDateTime, Visit> mockedDoctorTimetable = new TreeMap<>();
                Random random = new Random();
                for (int i = 0; i < 20; i++) {
                    int randomDay = random.nextInt(31);
                    for (int k = 0; k < 20; k++) {
                        int randomBusyTerm = random.nextInt(9);
                        mockedDoctorTimetable.put(LocalDateTime.now()
                                        .plusDays(randomDay)
                                        .withHour(10 + randomBusyTerm)
                                        .withMinute(0)
                                        .withSecond(0)
                                        .withNano(0)
                                , new Visit());
                        Reservation.getDoctorTimetable().put(doctor, mockedDoctorTimetable);
                    }
                }
            }
        }
        reservation.setLastVisit(LocalDate.now());
        return reservation;
    }
}
