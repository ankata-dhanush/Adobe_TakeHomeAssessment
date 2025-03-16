package com.adobe.romannumerals.controller;

import com.adobe.romannumerals.Dto.RomanNumeralDto;
import com.adobe.romannumerals.exception.RomanNumericConversionException;
import com.adobe.romannumerals.service.RomanNumeralService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller that provides an endpoint for converting numbers to Roman
 * numerals.
 */
@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/romannumeral")
@RequiredArgsConstructor
@Slf4j
public class RomanNumeralController {

    // Injected service to handle the conversion logic.
    private final RomanNumeralService romanNumeralService;

    /**
     * Converts a given integer to its corresponding Roman numeral.
     *
     * @param number the integer to be converted (provided as a query parameter)
     * @return ResponseEntity containing the converted Roman numeral wrapped in a
     *         DTO
     */
    @GetMapping
    public ResponseEntity<RomanNumeralDto> convertToRoman(@RequestParam("query") Integer number)
            throws RomanNumericConversionException {
        log.info("Received request to convert number: {}", number);
        // Perform the conversion using the service.
        String romanNumeral = romanNumeralService.convertToRoman(number);
        log.info("Successfully converted {} to {}", number, romanNumeral);
        // Construct and return the response.
        return ResponseEntity.ok(
                RomanNumeralDto.builder()
                        .input(String.valueOf(number))
                        .output(romanNumeral)
                        .build());
    }
}
