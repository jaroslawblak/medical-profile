package com.blak.medicalprofile.dao;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Timetable {
    public static final int MAX_TERMS_IN_DAY = 8;

    private Map<LocalDate, Set<Visit>> calendar = new TreeMap<>();

    public Timetable() {
        //jackson requirements
    }

    public Map<LocalDate, Set<Visit>> getCalendar() {
        return calendar;
    }

    public void setCalendar(Map<LocalDate, Set<Visit>> calendar) {
        this.calendar = calendar;
    }

    public Timetable calendar(final Map<LocalDate, Set<Visit>> calendar) {
        this.calendar = calendar;
        return this;
    }


    public void printCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.println(new SimpleDateFormat("MMMM yyyy").format(calendar.getTime()));
        System.out.println(" S  M  T  W  T  F  S");

        StringBuilder initialSpace = new StringBuilder();
        for (int i = 0; i < dayOfWeek - 1; i++) {
            initialSpace.append("   ");
        }
        System.out.print(initialSpace);

        for (int i = 0, dayOfMonth = 1; dayOfMonth <= daysInMonth; i++) {
            for (int j = ((i == 0) ? dayOfWeek - 1 : 0); j < 7 && (dayOfMonth <= daysInMonth); j++) {
                if (dayOfMonth < LocalDate.now().getDayOfMonth()) {
                    System.out.printf(FontColour.ANSI_BLACK.getValue() + "%2d " + FontColour.ANSI_RESET.getValue(), dayOfMonth);
                } else if (checkForFreeTerms(dayOfMonth)) {
                    System.out.printf(FontColour.ANSI_GREEN.getValue() + "%2d " + FontColour.ANSI_RESET.getValue(), dayOfMonth);
                } else {
                    System.out.printf(FontColour.ANSI_RED.getValue() + "%2d " + FontColour.ANSI_RESET.getValue(), dayOfMonth);
                }
                dayOfMonth++;
            }
            System.out.println();
        }
    }

    public Boolean checkForFreeTerms(int dayOfMonth) {
        LocalDate date = LocalDate.now().withDayOfMonth(dayOfMonth);
        if(this.getCalendar().containsKey(date)){
            return this.getCalendar().get(date).size() < MAX_TERMS_IN_DAY;
        }
        return true;
        }

    public Set<LocalTime> getAllFreeTermsInDay(int dayOfMonth) {
        Set<LocalTime> allTermsInDay = new TreeSet<>();
        for (int i = 10; i < 18; i++) {
            allTermsInDay.add(LocalTime.of(i, 0, 0, 0));
        }
        if(!this.getCalendar().containsKey(LocalDate.now().withDayOfMonth(dayOfMonth))){
            return allTermsInDay;
        }
        Set<LocalTime> busyTermsInDay = this.getCalendar()
                .get(LocalDate.now().withDayOfMonth(dayOfMonth))
                .stream()
                .map(Visit::getTime)
                .collect(Collectors.toSet());
        allTermsInDay.removeAll(busyTermsInDay);
        return allTermsInDay;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "calendar=" + calendar +
                '}';
    }
}