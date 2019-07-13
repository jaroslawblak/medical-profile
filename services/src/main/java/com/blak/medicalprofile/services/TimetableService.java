package com.blak.medicalprofile.services;

import com.blak.medicalprofile.dao.Timetable;
import com.blak.medicalprofile.dao.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class TimetableService {
    private Timetable timetable;

    public TimetableService(@Autowired Timetable timetable) {
        this.timetable = timetable;
    }


    public Timetable getCalendarForDoctor(int doctorId) {
        Map<LocalDate, Set<Visit>> filteredCalendar = new TreeMap<>();
        for (Map.Entry<LocalDate, Set<Visit>> entry: timetable.getCalendar().entrySet()) {
                filteredCalendar.put(
                        entry.getKey(),
                        entry.getValue().stream()
                                .filter(v -> v.getDoctor().getId() == doctorId)
                                .sorted(Visit::compareTo)
                                .collect(Collectors.toCollection(TreeSet::new)));
            }
        return new Timetable().calendar(filteredCalendar);
    }

    public Boolean addNewReservation(Visit visit) {
        LocalDate date = visit.getDate();
        return this.timetable.getCalendar().get(date).add(visit);
    }

}
