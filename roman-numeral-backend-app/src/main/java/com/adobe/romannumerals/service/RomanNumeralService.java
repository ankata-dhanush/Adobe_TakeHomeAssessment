package com.adobe.romannumerals.service;

import com.adobe.romannumerals.exception.RomanNumericConversionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for converting integers to Roman numerals.
 * Supports conversion for numbers in the range 1 to 3999.
 */
@Service
@Slf4j
public class RomanNumeralService {

    // Array representing integer values corresponding to Roman numeral symbols.
    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    // Array of Roman numeral symbols corresponding to the VALUES array.
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * Converts an integer to its Roman numeral representation.
     *
     * @param number the integer to convert (must be between 1 and 3999)
     * @return the Roman numeral representation of the given number
     * @throws IllegalArgumentException        if the number is outside the valid range (1-3999)
     * @throws RomanNumericConversionException if an unexpected error occurs during conversion
     */
    public String convertToRoman(int number) throws RomanNumericConversionException {
        // Validate the input number.
        if (number <= 0 || number > 3999) {
            log.error("Invalid number: {}. Roman numerals are only defined for 1 to 3999.", number);
            throw new IllegalArgumentException("Input must be between 1 and 3999.");
        }
        try {
            StringBuilder result = new StringBuilder();
            // Iterate through the VALUES array to determine the Roman numeral representation.
            for (int i = 0; i < VALUES.length; i++) {
                // Append corresponding Roman numeral symbols while the number is greater than or equal to the current VALUE.
                while (number >= VALUES[i]) {
                    result.append(SYMBOLS[i]);
                    number -= VALUES[i]; // Reduce the number accordingly.
                }
            }

            return result.toString();
        } catch (Exception e) {
            // Log the error and throw a custom exception.
            log.error("Error occurred while converting number {} to Roman numeral: {}", number, e.getMessage(), e);
            throw new RomanNumericConversionException("Exception occurred while converting number to Roman numeral.");
        }
    }
}
