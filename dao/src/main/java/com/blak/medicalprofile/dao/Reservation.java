package com.blak.medicalprofile.dao;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class Reservation {
    private static final Map<LocalDate, Visit> timetable = new LinkedHashMap<>();

    public Reservation() {
        this.makeRandomTermsBusy();
    }


    private void makeRandomTermsBusy() {
        Random random = new Random();
        for(int i =0; i < 20; i++){
            random.nextInt(40);
            timetable.put(LocalDate.now().plusDays(i), null);
        }
    }

    public static Map<LocalDate, Visit> getTimetable() {
        return new LinkedHashMap<>(timetable);
    }

    public void printCalendar() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1); //Set the day of month to 1
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); //get day of week for 1st of month
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.println(new SimpleDateFormat("MMMM YYYY").format(calendar.getTime()));
        System.out.println(" S  M  T  W  T  F  S");

        String initialSpace = "";
        for (int i = 0; i < dayOfWeek - 1; i++) {
            initialSpace += "   ";
        }
        System.out.print(initialSpace);

        for (int i = 0, dayOfMonth = 1; dayOfMonth <= daysInMonth; i++) {
            for (int j = ((i == 0) ? dayOfWeek - 1 : 0); j < 7 && (dayOfMonth <= daysInMonth); j++) {
                System.out.printf("%2d ", dayOfMonth);
                dayOfMonth++;
            }
            System.out.println();
        }
    }
}
