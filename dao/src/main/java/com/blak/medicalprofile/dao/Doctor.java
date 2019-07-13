package com.blak.medicalprofile.dao;

import java.io.Serializable;
import java.util.Objects;

public class Doctor extends User {
    private DoctorType doctorType;

    public Doctor(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    public Doctor() {
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + " " + this.getDoctorType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return doctorType == doctor.doctorType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), doctorType);
    }
}
