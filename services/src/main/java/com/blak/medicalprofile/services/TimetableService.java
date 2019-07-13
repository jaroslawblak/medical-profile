package com.blak.medicalprofile.services;

import com.blak.medicalprofile.dao.Timetable;
import com.blak.medicalprofile.dao.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TimetableService {
    public static final int MAX_TERMS_IN_DAY = 8;

    private Timetable timetable;

    public TimetableService(@Autowired Timetable timetable) {
        this.timetable = timetable;
    }


    public Timetable getCalendarForDoctor(int doctorId) {
        Map<LocalDate, Set<Visit>> filteredCalendar = timetable.getCalendar()
                .entrySet()
                .stream()
                .filter(calendar -> calendar.getValue().stream().filter(isAllTermsBusy(doctorId))
                        && calendar.getValue().size() != MAX_TERMS_IN_DAY).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new Timetable().calendar(filteredCalendar);
    }

    public static Predicate<Set<Visit>> isAllTermsBusy(int doctorId){
        return set-> set.stream().filter(v -> v.getDoctor().getId() = doctorId);
    }


}
