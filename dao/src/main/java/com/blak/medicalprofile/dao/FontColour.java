package com.blak.medicalprofile.dao;

public class FontColour {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public static String getAnsiGreen() {
        return ANSI_GREEN;
    }

    public static String getAnsiReset() {
        return ANSI_RESET;
    }

    public static String getAnsiRed() {
        return ANSI_RED;
    }

    public static String getAnsiBlack() {
        return ANSI_BLACK;
    }
}
