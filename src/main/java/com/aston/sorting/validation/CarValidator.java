package com.aston.sorting.validation;

import java.time.Instant;
import java.time.ZoneId;

public class CarValidator {

    private CarValidator() {
    }

    public static boolean validateHorsePower(int horsePower) {
        // TODO: реализовать валидацию (например, > 0)
        return 0 < horsePower && horsePower <= 2500 ;
    }

    public static boolean validateModel(String model) {
        // TODO: реализовать валидацию (не пустая строка)
        if (model == null) return false;
        return  ! model.isBlank();
    }

    public static boolean validateYear(int year) {
        // TODO: реализовать валидацию (например, >= 1886 и <= текущий год)
        int currentYear = Instant.now().atZone(ZoneId.systemDefault()).getYear();
        return 1886 < year && year <= currentYear;
    }
}
