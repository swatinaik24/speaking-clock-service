package com.speakingclock.enums;

import com.speakingclock.exception.InvalidTimeFormatException;

public enum Number {
    ZERO("zero"), ONE("one"), TWO("two"), THREE("three"), FOUR("four"), FIVE("five"), SIX("six"),
    SEVEN("seven"), EIGHT("eight"), NINE("nine"), TEN("ten"), ELEVEN("eleven"), TWELVE("twelve"),
    THIRTEEN("thirteen"), FOURTEEN("fourteen"), FIFTEEN("fifteen"), SIXTEEN("sixteen"),
    SEVENTEEN("seventeen"), EIGHTEEN("eighteen"), NINETEEN("nineteen");

    private final String word;

    Number(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public static Number fromHour(int hour) {
        if (hour < 0 || hour > 12) {
            throw new InvalidTimeFormatException("Invalid hour value: " + hour);
        }
        return values()[hour];
    }

    public static Number fromMinute(int minute) {
        if (minute < 0 || minute > 59) {
            throw new InvalidTimeFormatException("Invalid minute value: " + minute);
        }
        return values()[minute];
    }
}






