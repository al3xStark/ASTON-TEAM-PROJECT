package com.aston.sorting.concurrent;

import org.junit.jupiter.api.Test;

class ConcurrentCounterTest {

    @Test
    void shouldReturnCorrectCount() {
        // TODO: создать список с известным числом вхождений target,
        //       проверить что countOccurrences возвращает правильное число
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        // TODO: передать пустой список,
        //       проверить что возвращается 0
    }

    @Test
    void shouldReturnZeroWhenNoMatches() {
        // TODO: передать список без вхождений target,
        //       проверить что возвращается 0
    }

    @Test
    void shouldReturnSizeWhenAllElementsMatch() {
        // TODO: создать список где все элементы равны target,
        //       проверить что результат равен размеру списка
    }

    @Test
    void shouldReturnCorrectCountForLargeList() {
        // TODO: создать список из 10_000 элементов, где ровно 3_000 совпадают с target,
        //       проверить что результат равен 3_000.
        //       Этот тест важен именно для многопоточной реализации — баги
        //       race condition проявляются чаще всего на больших объёмах данных.
    }
}
