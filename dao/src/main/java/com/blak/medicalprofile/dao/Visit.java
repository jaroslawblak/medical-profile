package com.blak.medicalprofile.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Visit implements Comparable<Visit>{
    private LocalDate date;
    private LocalTime time;
    private Patient patient;
    private Doctor doctor;
    private VisitType visitType;

    public Visit(LocalDate date, LocalTime time, Patient patient, Doctor doctor, VisitType visitType) {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
        this.visitType = visitType;
    }

    public Visit() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(date, visit.date) &&
                Objects.equals(time, visit.time) &&
                Objects.equals(patient, visit.patient) &&
                Objects.equals(doctor, visit.doctor) &&
                visitType == visit.visitType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, patient, doctor, visitType);
    }

    @Override
    public int compareTo(Visit visit) {
        int compareByFirstName = this.doctor.getFirstName().compareTo(visit.getDoctor().getFirstName());
        int compareBySecondName = this.doctor.getLastName().compareTo(visit.getDoctor().getLastName());
        if(compareByFirstName !=0) {
            return compareByFirstName;
        }else if (compareBySecondName != 0){
            return compareBySecondName;
        }
        return this.time.compareTo(visit.time);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "date=" + date +
                ", time=" + time +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", visitType=" + visitType +
                '}';
    }
}
