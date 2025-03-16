package com.adobe.romannumerals.service;

import com.adobe.romannumerals.exception.RomanNumericConversionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralServiceTest {

    private RomanNumeralService romanNumeralService;

    @BeforeEach
    void setUp() {
        romanNumeralService = new RomanNumeralService();
    }

    /**
     * Test valid conversions from integer to Roman numeral.
     */
    @Test
    void testConvertToRoman_ValidNumbers() throws RomanNumericConversionException {
        assertEquals("I", romanNumeralService.convertToRoman(1));
        assertEquals("IV", romanNumeralService.convertToRoman(4));
        assertEquals("IX", romanNumeralService.convertToRoman(9));
        assertEquals("XL", romanNumeralService.convertToRoman(40));
        assertEquals("XC", romanNumeralService.convertToRoman(90));
        assertEquals("CD", romanNumeralService.convertToRoman(400));
        assertEquals("CM", romanNumeralService.convertToRoman(900));
        assertEquals("MMMCMXCIX", romanNumeralService.convertToRoman(3999));
    }

    /**
     * Test lower boundary cases (valid: 1, 3999).
     */
    @Test
    void testConvertToRoman_BoundaryValues() throws RomanNumericConversionException {
        assertEquals("I", romanNumeralService.convertToRoman(1));
        assertEquals("MMMCMXCIX", romanNumeralService.convertToRoman(3999));
    }

    /**
     * Test invalid numbers (less than 1 and greater than 3999) should throw IllegalArgumentException.
     */
    @Test
    void testConvertToRoman_InvalidNumbers_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> romanNumeralService.convertToRoman(0));
        assertThrows(IllegalArgumentException.class, () -> romanNumeralService.convertToRoman(-1));
        assertThrows(IllegalArgumentException.class, () -> romanNumeralService.convertToRoman(4000));
    }

    /**
     * Test for unexpected errors that should result in a RomanNumericConversionException.
     */
    @Test
    void testConvertToRoman_UnexpectedExceptionHandling() {
        try {
            romanNumeralService.convertToRoman(10000); // Out of range
            fail("Expected IllegalArgumentException but no exception was thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException, "Unexpected exception type: " + e.getClass());
        }
    }
}
