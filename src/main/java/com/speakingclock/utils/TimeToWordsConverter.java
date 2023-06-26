package com.speakingclock.utils;


import com.speakingclock.enums.Number;
import com.speakingclock.enums.Ten;
import com.speakingclock.exception.InvalidTimeFormatException;


public class TimeToWordsConverter {
    public static String convertTimeToWords(String time) {
        if (time == null || time.isEmpty()) {
            throw new InvalidTimeFormatException("Time must not be null or empty");
        }

        String[] parts = time.split(":");
        if (parts.length != 2) {
            throw new InvalidTimeFormatException("Invalid time format. Expected HH:mm");
        }

        int hours;
        int minutes;
        try {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTimeFormatException("Invalid time format. Hours and minutes must be integers");
        }

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidTimeFormatException("Invalid time. Hours must be between 0-23 and minutes between 0-59");
        }

        StringBuilder timeInWords = new StringBuilder("It's ");
        if (hours == 0 && minutes == 0) {
            timeInWords.append("Midnight");
        } else if (hours == 12 && minutes == 0) {
            timeInWords.append("Midday");
        } else {
            timeInWords.append(convertHoursToWords(hours))
                    .append(" ").append(convertMinutesToWords(minutes))
                    .append(" ").append(getTimeOfDay(hours));;
        }

        return timeInWords.toString();
    }

    private static String convertHoursToWords(int hours) {
        if (hours > 12) {
            hours -= 12;
        }

        return Number.fromHour(hours).getWord();
    }

    private static String getTimeOfDay(int hours) {
        if (hours >= 5 && hours < 12) {
            return "in the morning";
        } else if (hours >= 12 && hours < 17) {
            return "in the afternoon";
        } else if (hours >= 17 && hours < 20) {
            return "in the evening";
        } else {
            return "in the night";
        }
    }

    private static String convertMinutesToWords(int minutes) {
        if (minutes == 0) {
            return "o'clock";
        } else if (minutes < 20) {
            return Number.fromMinute(minutes).getWord();
        } else {
            int tensDigit = minutes / 10;
            int onesDigit = minutes % 10;

            if (onesDigit == 0) {
                return Ten.fromDigit(tensDigit).getWord();
            } else {
                return Ten.fromDigit(tensDigit).getWord() + " " + Number.fromMinute(onesDigit).getWord();
            }
        }
    }
}






