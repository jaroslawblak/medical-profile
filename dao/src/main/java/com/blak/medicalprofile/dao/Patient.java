package com.blak.medicalprofile.dao;

import java.time.LocalDate;
import java.util.Objects;

public class Patient extends User{
    private LocalDate lastVisit;

    public Patient() {
        //jackson requirements
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(lastVisit, patient.lastVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lastVisit);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "lastVisit=" + lastVisit +
                '}';
    }
}
