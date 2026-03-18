package com.aston.sorting.validation;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class CarValidator {

    private CarValidator() {
    }

    public static boolean validateHorsePower(int horsePower) {
        return 0 < horsePower;
    }

    public static boolean validateModel(String model) {
        if (model == null) return false;
        return  ! model.isBlank();
    }

    public static boolean validateYear(int year) {
        int currentYear = LocalDate.now().getYear();
        return 1886 <= year && year <= currentYear;
    }
}
