package com.speakingclock.controller;

import com.speakingclock.dto.ResponseDTO;
import com.speakingclock.service.SpeakingClockService;
import com.speakingclock.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/speaking-clock")
public class SpeakingClockController {
    @Autowired
    private SpeakingClockService speakingClockService;

    @GetMapping(value = "/default")
    public ResponseEntity<ResponseDTO> convertCurrentTime() {
        String timeInWords = speakingClockService.convertTimeToWords(TimeUtils.getCurrentTime());
        return new ResponseEntity<>(createSuccessResponse(timeInWords), HttpStatus.OK);
    }

    @GetMapping(value = "/input/{time}")
    public ResponseEntity<ResponseDTO> convertUserInputToWords(@PathVariable("time") String time) {
        String timeInWords = speakingClockService.convertTimeToWords(time);
        Optional<String> optionalTimeInWords = Optional.ofNullable(timeInWords);
        if (optionalTimeInWords.isPresent()) {
            return ResponseEntity.ok(createSuccessResponse(optionalTimeInWords.get()));
        } else {
            return new ResponseEntity(createErrorResponse("Invalid time format "),HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseDTO createSuccessResponse(String message) {
        ResponseDTO success = new ResponseDTO();
        success.setTimestamp(LocalDateTime.now());
        success.setStatus(HttpStatus.OK.value());
        success.setClockMessage(message);
        return success;
    }

    private ResponseDTO createErrorResponse(String errorMessage) {
        ResponseDTO error = new ResponseDTO();
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(errorMessage);
        return error;
    }
}

