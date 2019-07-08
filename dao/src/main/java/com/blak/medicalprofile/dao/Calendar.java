package com.blak.medicalprofile.dao;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@Component
public class Calendar {
    private static Map<Doctor, Map<LocalDate, Boolean>> doctorTimetable;
    private static Map<LocalDate, Boolean> timetable;

    public Calendar() {
        this.timetable = new LinkedHashMap<>();
        this.makeRandomTermsBusy();
    }


    private void makeRandomTermsBusy() {
        Random random = new Random();
        for(int i =0; i < 20; i++){
            random.nextInt(40);
            timetable.put(LocalDate.now().plusDays(i), Boolean.TRUE);
        }
    }

    public static Map<LocalDate, Boolean> getTimetable() {
        return timetable;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Calendar: \n");
        for (Map.Entry<LocalDate, Boolean> entry: timetable.entrySet()) {
            stringBuilder.append("Date ").append(entry.getKey()).append(" Free: ").append(entry.getValue()).append("\n");
        }
        return stringBuilder.toString();
    }
}
