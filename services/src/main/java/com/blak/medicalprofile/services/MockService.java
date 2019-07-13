package com.blak.medicalprofile.services;

import com.blak.medicalprofile.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class MockService {

    private Timetable timetable;

    public MockService(@Autowired Timetable timetable) {
        this.timetable = timetable;
    }

    public static List<Doctor> getMockedDoctorList() {


        Doctor firstDoctor = new Doctor(DoctorType.CARDIOLOGIST);
        firstDoctor.id(1)
                .firstName("Artur")
                .lastName("Jedrzejczyk")
                .birthday(LocalDate.of(1976, 7, 30))
                .pesel("453965432")
                .userKey("1121343232432");

        Doctor secondDoctor = new Doctor(DoctorType.DENTIST);
        secondDoctor.id(2)
                .firstName("Jakub")
                .lastName("Blaszczykowski")
                .birthday(LocalDate.of(1996, 10, 2))
                .pesel("35437687")
                .userKey("987987755434");

        Doctor thirdDoctor = new Doctor(DoctorType.ORTHOPAEDIST);
        thirdDoctor.id(3)
                .firstName("Artur")
                .lastName("Boruc")
                .birthday(LocalDate.of(1990, 12, 7))
                .pesel("76456544")
                .userKey("43243535334");

        Doctor fourthDoctor = new Doctor(DoctorType.SURGEON);
        fourthDoctor.id(4)
                .firstName("Robert")
                .lastName("Lewandowski")
                .birthday(LocalDate.of(1982, 2, 21))
                .pesel("53223423")
                .userKey("4675765876");

        return new ArrayList<>(Arrays.asList(firstDoctor, secondDoctor, thirdDoctor, fourthDoctor));
    }

    public Timetable getMockedCalendar() {
        if (this.timetable.getCalendar().isEmpty()) {
            for (int i = 0; i <= 31; i++) {
                LocalDate localDate = LocalDate.now().plusDays(i);
                if (new Random().nextInt(31) > i) {
                    this.timetable.getCalendar().put(localDate, mockTermsInDay(localDate));
                    continue;
                }
            }
        }
        return this.timetable;
    }

    private static Set<Visit> mockTermsInDay(LocalDate date) {
        TreeSet<Visit> visits = new TreeSet<>();
        for (Doctor doctor : getMockedDoctorList()) {
            for (int k = new Random().nextInt(3); k < 8; k++) {
                visits.add(new Visit(date, LocalTime.of(10 + k, 0), null, doctor, VisitType.REGULAR));
            }
        }
        return visits;
    }
}
