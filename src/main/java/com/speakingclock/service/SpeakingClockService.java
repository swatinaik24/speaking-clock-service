package com.speakingclock.service;

import com.speakingclock.utils.TimeToWordsConverter;
import org.springframework.stereotype.Service;

@Service
public class SpeakingClockService {
    public String convertTimeToWords(String currentTime) {
        return TimeToWordsConverter.convertTimeToWords(currentTime);
    }
}
