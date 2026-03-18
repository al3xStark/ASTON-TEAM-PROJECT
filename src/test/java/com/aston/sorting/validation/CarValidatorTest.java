package com.aston.sorting.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class CarValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void horsePowerShouldBeInvalidWhenZeroOrNegative(int horsePower) {
        boolean isValid = CarValidator.validateHorsePower(horsePower);

        assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 20, 200, 1500, 2500, 3000})
    void horsePowerShouldBeValidWhenPositive(int horsePower) {
            boolean isValid = CarValidator.validateHorsePower(horsePower);

        assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(ints = {1880, 1885})
    void yearShouldBeInvalidWhenBefore1886(int year) {
        boolean isValid = CarValidator.validateYear(year);

        assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(ints = {1886, 1887, 1900, 1960, 2000, 2026})
    void yearShouldBeValidWhenBetween1886AndCurrentYear(int year) {
        boolean isValid = CarValidator.validateYear(year);

        assertTrue(isValid);
    }

    @Test
    void yearShouldBeInvalidWhenInFuture() {
        int nextYear = Instant.now().atZone(ZoneId.systemDefault()).getYear() + 1;
        int inTwoYears = Instant.now().atZone(ZoneId.systemDefault()).getYear() + 2;

        boolean isValidNextYear = CarValidator.validateYear(nextYear);
        boolean isValidInTwoYears = CarValidator.validateYear(inTwoYears);

        assertFalse(isValidNextYear);
        assertFalse(isValidInTwoYears);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @NullSource
    void modelShouldBeInvalidWhenEmptyOrNullOrBlank(String model) {
        boolean isValid = CarValidator.validateModel(model);

        assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"model", "23hj", "23-xc", "very long model name"})
    void modelShouldBeValidWhenNotEmpty(String model) {
        boolean isValid = CarValidator.validateModel(model);

        assertTrue(isValid);
    }
}
