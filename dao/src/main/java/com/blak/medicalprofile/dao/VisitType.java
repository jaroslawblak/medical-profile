package com.blak.medicalprofile.dao;

public enum VisitType {
    MONITORING("Monitoring"),
    REGULAR("Regular Visit"),
    DISEASE("Disease"),
    ORTHOPAEDIST("Orthopaedist");

    private String value;

    VisitType(String visitType) {
        this.value = visitType;
    }

    public String getValue() {
        return this.value;
    }

    public static VisitType fromString(String stringOfVisitType) {
        for (VisitType visitType : VisitType.values()) {
            if (visitType.getValue().equalsIgnoreCase(stringOfVisitType.toUpperCase())) {
                return visitType;
            }
        }
        return null;
    }
}
