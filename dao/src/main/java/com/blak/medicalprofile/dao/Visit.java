package com.blak.medicalprofile.dao;

import java.time.LocalDate;
import java.time.LocalTime;

public class Visit {
    private Doctor doctor;
    private LocalDate date;
    private LocalTime time;
    private User user;
    private VisitType visitType;

    public Visit(Doctor doctor, LocalDate date, LocalTime time, User user, VisitType visitType) {
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.user = user;
        this.visitType = visitType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VisitType getVisitType() {
        return visitType;
    }

    public void setVisitType(VisitType visitType) {
        this.visitType = visitType;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
