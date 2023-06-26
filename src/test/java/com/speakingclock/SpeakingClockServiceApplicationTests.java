package com.speakingclock;

import com.speakingclock.controller.SpeakingClockController;
import com.speakingclock.dto.ResponseDTO;
import com.speakingclock.service.SpeakingClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpeakingClockServiceApplicationTests {

    @Mock
    private SpeakingClockService speakingClockService;


    @InjectMocks
    private SpeakingClockController speakingClockController;


    @BeforeEach
    public void setUp() {}

    @Test
    public void testConvertTimeToWords_MiddayTime_ReturnsMiddayString() {
        // Arrange
        String inputTime = "12:00";
        String expectedOutput = "It's Midday";
        when(speakingClockService.convertTimeToWords(inputTime)).thenReturn(expectedOutput);

        // Act
        ResponseEntity<ResponseDTO> response = speakingClockController.convertUserInputToWords(inputTime);

        // Assert
        assertEquals(expectedOutput, response.getBody().getClockMessage());
    }

    @Test
    public void testConvertTimeToWords_MidnightTime_ReturnsMidnightString() {
        // Arrange
        String inputTime = "00:00";
        String expectedOutput = "It's Midnight";
        when(speakingClockService.convertTimeToWords(inputTime)).thenReturn(expectedOutput);

        // Act
        ResponseEntity<ResponseDTO> response = speakingClockController.convertUserInputToWords(inputTime);

        // Assert
        assertEquals(expectedOutput, response.getBody().getClockMessage());
    }

    @Test
    public void testConvertTimeToWords_ReturnsTimeInWordsString() {
        // Arrange
        String inputTime = "13:34";
        String expectedOutput = "It's one thirty four in the afternoon";
        when(speakingClockService.convertTimeToWords(inputTime)).thenReturn(expectedOutput);

        // Act
        ResponseEntity<ResponseDTO> response = speakingClockController.convertUserInputToWords(inputTime);

        // Assert
        assertEquals(expectedOutput, response.getBody().getClockMessage());
    }

    @Test
    public void testConvertUserInputToWords_InvalidInput_ReturnsBadRequest() {
        // Arrange
        String userInput = "25:00";
        // Act
        ResponseEntity<ResponseDTO> response = speakingClockController.convertUserInputToWords(userInput);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
    }

    @Test
    public void testConvertUserInputToWords_NullInput_ReturnsBadRequest() {
        // Arrange
        String userInput = null;

        // Act
        ResponseEntity<ResponseDTO> response = speakingClockController.convertUserInputToWords(userInput);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
    }


    @Test
    public void testConvertTimeToWords_IllegalArgument_ReturnsBadRequest() {
        // Arrange
        List<String> illegalInputs = Arrays.asList("01:", ":40", "24:00", "00:60", "12:61");
        for (String inputTime : illegalInputs) {
            // Act
            ResponseEntity<ResponseDTO> response = speakingClockController.convertUserInputToWords(inputTime);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        }
    }

}

