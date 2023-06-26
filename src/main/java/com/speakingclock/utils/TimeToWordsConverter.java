package com.speakingclock.utils;


import com.speakingclock.enums.Number;
import com.speakingclock.enums.Ten;
import com.speakingclock.exception.InvalidTimeFormatException;


import java.util.function.Function;

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

        String timeOfDay = getTimeOfDay(hours);

        String timeInWords = "It's " +
                (hours == 0 && minutes == 0 ? "Midnight" :
                        (hours == 12 && minutes == 0 ? "Midday" :
                                convertHoursToWords(hours) + " " +
                                        convertMinutesToWords(minutes) + " " +
                                        timeOfDay));

        return timeInWords;
    }

    private static String convertHoursToWords(int hours) {
        if (hours > 12) {
            hours -= 12;
        }

        return Number.fromHour(hours).getWord();
    }

    private static String getTimeOfDay(int hours) {
        Function<Integer, String> getTimeOfDayFn = hour -> {
            if (hour >= 5 && hour < 12) {
                return "in the morning";
            } else if (hour >= 12 && hour < 17) {
                return "in the afternoon";
            } else if (hour >= 17 && hour < 20) {
                return "in the evening";
            } else {
                return "in the night";
            }
        };

        return getTimeOfDayFn.apply(hours);
    }

    private static String convertMinutesToWords(int minutes) {
        if (minutes == 0) {
            return "o'clock";
        } else if (minutes < 20) {
            return Number.fromMinute(minutes).getWord();
        } else {
            int tensDigit = minutes / 10;
            int onesDigit = minutes % 10;

            Function<Integer, String> getTenWordFn = tens -> {
                if (onesDigit == 0) {
                    return Ten.fromDigit(tens).getWord();
                } else {
                    return Ten.fromDigit(tens).getWord() + " " + Number.fromMinute(onesDigit).getWord();
                }
            };

            return getTenWordFn.apply(tensDigit);
        }
    }
}






