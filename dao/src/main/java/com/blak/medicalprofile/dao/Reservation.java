package com.blak.medicalprofile.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Reservation implements Serializable {
    static final long serialVersionUID = 1L;
    private static final Map<Doctor, Map<LocalDate, Set<Visit>>> doctorTimetable = new LinkedHashMap<>();

    public Reservation() {
    }

    public static Map<Doctor, Map<LocalDate, Set<Visit>>> getDoctorTimetable() {
        return doctorTimetable;
    }

    public void printCalendar(Doctor doctor) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.println(new SimpleDateFormat("MMMM YYYY").format(calendar.getTime()));
        System.out.println(" S  M  T  W  T  F  S");

        StringBuilder initialSpace = new StringBuilder();
        for (int i = 0; i < dayOfWeek - 1; i++) {
            initialSpace.append("   ");
        }
        System.out.print(initialSpace);

        for (int i = 0, dayOfMonth = 1; dayOfMonth <= daysInMonth; i++) {
            for (int j = ((i == 0) ? dayOfWeek - 1 : 0); j < 7 && (dayOfMonth <= daysInMonth); j++) {
                if (dayOfMonth < LocalDate.now().getDayOfMonth()) {
                    System.out.printf(FontColour.getAnsiBlack() + "%2d " + FontColour.getAnsiReset(), dayOfMonth);
                } else if (checkForFreeTerms(doctor, dayOfMonth)) {
                    System.out.printf(FontColour.getAnsiRed() + "%2d " + FontColour.getAnsiReset(), dayOfMonth);
                } else {
                    System.out.printf(FontColour.getAnsiGreen() + "%2d " + FontColour.getAnsiReset(), dayOfMonth);
                }
                dayOfMonth++;
            }
            System.out.println();
        }
    }

    public Boolean checkForFreeTerms(Doctor doctor, int dayOfMonth) throws NullPointerException {
        LocalDate date = LocalDate.now().withDayOfMonth(dayOfMonth);

        if (getDoctorTimetable().get(doctor).get(date) != null) {
            return (getDoctorTimetable().get(doctor).get(date).size() == 8);
        }
        return false;
    }

    public Set<LocalTime> getAllAvailableTerms() {
        Set<LocalTime> allTermsInDay = new TreeSet<>();
        for (int i = 10; i <= 18; i++) {
            allTermsInDay.add(LocalTime.of(i, 0, 0, 0));
        }
        return allTermsInDay;
    }

//    public Set<LocalDateTime> getFreeTermsInDay(Doctor doctor, int dayOfMonth){
//        doctorTimetable.get(doctor).get(LocalDate.now().withDayOfMonth(dayOfMonth))
//    }
}
