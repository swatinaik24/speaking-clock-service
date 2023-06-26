package com.speakingclock.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    public static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime today = LocalTime.now();
        return today.format(formatter);
    }
}
