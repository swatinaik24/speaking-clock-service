package com.speakingclock.enums;


import com.speakingclock.exception.InvalidTimeFormatException;

public enum Ten {
    TWENTY("twenty"), THIRTY("thirty"), FORTY("forty"), FIFTY("fifty");

    private final String word;

    Ten(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public static Ten fromDigit(int digit) {
        if (digit < 2 || digit > 5) {
            throw new InvalidTimeFormatException("Invalid digit value: " + digit);
        }
        return values()[digit - 2];
    }
}


