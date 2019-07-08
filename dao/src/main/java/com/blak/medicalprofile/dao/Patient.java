package com.blak.medicalprofile.dao;

import java.time.LocalDate;

public class Patient extends User{
    private LocalDate lastVisit;

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }
}
