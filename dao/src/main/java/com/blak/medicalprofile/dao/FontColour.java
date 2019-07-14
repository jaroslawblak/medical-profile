package com.blak.medicalprofile.dao;

public enum FontColour {
    ANSI_GREEN("\u001B[32m"),
    ANSI_RESET("\u001B[0m"),
    ANSI_RED("\u001B[31m"),
    ANSI_BLACK("\u001B[30m");

    private String value;

    FontColour(String fontColour) {
        this.value = fontColour;
    }

    public String getValue() {
        return this.value;
    }

    public static FontColour fromString(String stringOfFontColour) {
        for (FontColour fontColour : FontColour.values()) {
            if (fontColour.getValue().equalsIgnoreCase(stringOfFontColour)) {
                return fontColour;
            }
        }
        return null;
    }
}


