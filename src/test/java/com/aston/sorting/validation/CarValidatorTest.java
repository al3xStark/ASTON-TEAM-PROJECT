package com.aston.sorting.validation;

import org.junit.jupiter.api.Test;

class CarValidatorTest {

    @Test
    void horsePowerShouldBeInvalidWhenZero() {
        // TODO: передать 0, проверить что validateHorsePower возвращает false
    }

    @Test
    void horsePowerShouldBeValidWhenPositive() {
        // TODO: передать положительное число, проверить что возвращает true
    }

    @Test
    void yearShouldBeInvalidWhenBefore1886() {
        // TODO: передать 1885, проверить что validateYear возвращает false
    }

    @Test
    void yearShouldBeValidWhen1886() {
        // TODO: передать 1886, проверить что validateYear возвращает true
    }

    @Test
    void yearShouldBeValidWhenCurrentYear() {
        // TODO: передать текущий год, проверить что validateYear возвращает true
    }

    @Test
    void yearShouldBeInvalidWhenInFuture() {
        // TODO: передать год больше текущего, проверить что validateYear возвращает false
    }

    @Test
    void modelShouldBeInvalidWhenEmpty() {
        // TODO: передать пустую строку, проверить что validateModel возвращает false
    }

    @Test
    void modelShouldBeInvalidWhenNull() {
        // TODO: передать null, проверить что validateModel возвращает false
    }

    @Test
    void modelShouldBeValidWhenNotEmpty() {
        // TODO: передать непустую строку, проверить что validateModel возвращает true
    }

    @Test
    void modelShouldBeInvalidWhenBlank() {
        // TODO: передать строку из пробелов "   ",
        //       проверить что validateModel возвращает false
    }
}
