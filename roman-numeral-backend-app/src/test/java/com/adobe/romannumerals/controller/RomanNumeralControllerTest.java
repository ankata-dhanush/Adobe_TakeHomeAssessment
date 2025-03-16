package com.adobe.romannumerals.controller;

import com.adobe.romannumerals.Dto.RomanNumeralDto;
import com.adobe.romannumerals.exception.RomanNumericConversionException;
import com.adobe.romannumerals.service.RomanNumeralService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class RomanNumeralControllerTest {

    @InjectMocks
    private RomanNumeralController romanNumeralController;

    @Mock
    private RomanNumeralService romanNumeralService;

    /**
     * Test case for successful conversion of an integer to a Roman numeral.
     */
    @Test
    void testConvertToRoman_Success() throws RomanNumericConversionException {
        // Given
        int inputNumber = 10;
        String expectedRoman = "X";

        // Mocking service behavior
        when(romanNumeralService.convertToRoman(inputNumber)).thenReturn(expectedRoman);

        // When
        ResponseEntity<RomanNumeralDto> response = romanNumeralController.convertToRoman(inputNumber);

        // Then
        assertEquals(200, response.getStatusCodeValue()); // Check HTTP status
        assertNotNull(response.getBody());
        assertEquals("10", response.getBody().getInput());
        assertEquals("X", response.getBody().getOutput());
    }

    /**
     * Test case for handling an invalid number (out of range).
     */
    @Test
    void testConvertToRoman_InvalidNumber_ThrowsException() throws RomanNumericConversionException {
        // Given
        int invalidNumber = 4000;

        // Mock service to throw an exception
        doThrow(new IllegalArgumentException("Input must be between 1 and 3999."))
                .when(romanNumeralService).convertToRoman(invalidNumber);

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                romanNumeralController.convertToRoman(invalidNumber)
        );

        assertEquals("Input must be between 1 and 3999.", exception.getMessage());
    }

    /**
     * Test case for handling an unexpected service exception.
     */
    @Test
    void testConvertToRoman_ServiceException() throws RomanNumericConversionException {
        // Given
        int number = 50;

        // Mock service to throw custom exception
        doThrow(new RomanNumericConversionException("Unexpected error occurred"))
                .when(romanNumeralService).convertToRoman(number);

        // When & Then
        RomanNumericConversionException exception = assertThrows(RomanNumericConversionException.class, () ->
                romanNumeralController.convertToRoman(number)
        );

        assertEquals("Unexpected error occurred", exception.getMessage());
    }
}
