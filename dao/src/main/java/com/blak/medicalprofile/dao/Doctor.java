package com.blak.medicalprofile.dao;

public class Doctor extends User {
    private DoctorType doctorType;

    public Doctor(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }
}
