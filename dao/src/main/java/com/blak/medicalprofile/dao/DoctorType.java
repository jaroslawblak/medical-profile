package com.blak.medicalprofile.dao;

public enum DoctorType {
    CARDIOLOGIST("Cardiologist"),
    SURGEON("Surgeon"),
    DENTIST("Dentist"),
    ORTHOPAEDIST("Orthopaedist");

    private String value;

    DoctorType(String doctorType) {
        this.value = doctorType;
    }

    public String getValue() {
        return this.value;
    }

    public static DoctorType fromString(String StringOfDoctorType) {
        for (DoctorType doctorType : DoctorType.values()) {
            if (doctorType.getValue().equalsIgnoreCase(StringOfDoctorType)) {
                return doctorType;
            }
        }
        return null;
    }
}
