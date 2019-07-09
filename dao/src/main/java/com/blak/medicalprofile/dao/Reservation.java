package com.blak.medicalprofile.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Reservation implements Serializable {
    static final long serialVersionUID = 1L;
    private static final Map<Doctor, Map<LocalDateTime, Visit>> doctorTimetable = new LinkedHashMap<>();
    private LocalDate lastVisit;

    public Reservation() {
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    public static Map<Doctor, Map<LocalDateTime, Visit>> getDoctorTimetable() {
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

    private Boolean checkForFreeTerms(Doctor doctor, int dayOfMonth) {
        Set<LocalDateTime> allTermsInDay = new TreeSet<>();
        for (int i = 10; i <= 18; i++) {
            allTermsInDay.add(LocalDateTime.now()
                    .withDayOfMonth(dayOfMonth)
                    .withHour(i)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0));
        }
        return getDoctorTimetable().get(doctor).keySet().containsAll(allTermsInDay);
    }
}
