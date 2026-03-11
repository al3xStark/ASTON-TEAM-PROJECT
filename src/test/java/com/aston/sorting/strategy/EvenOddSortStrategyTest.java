package com.aston.sorting.strategy;

import org.junit.jupiter.api.Test;

// Стратегия сортирует по полю horsePower:
// элементы с чётным horsePower сортируются в натуральном порядке,
// элементы с нечётным horsePower остаются на исходных позициях.
class EvenOddSortStrategyTest {

    @Test
    void evenElementsShouldBeSortedAndOddShouldRemainInPlace() {
        // TODO: создать список Car со смешанными horsePower (чётные и нечётные),
        //       запустить EvenOddSortStrategy,
        //       проверить что чётные отсортированы по возрастанию horsePower,
        //       а нечётные стоят на тех же индексах, что и до сортировки.
        //
        // Пример входного списка (horsePower): [3, 4, 1, 8, 2]
        // Нечётные (индексы 0, 2): horsePower = 3, 1 — не трогаем
        // Чётные   (индексы 1, 3, 4): horsePower = 4, 8, 2 → после сортировки: 2, 4, 8
        // Ожидаемый результат:          [3, 2, 1, 4, 8]
    }

    @Test
    void shouldHandleAllEvenElements() {
        // TODO: создать список только с чётными horsePower,
        //       проверить что список полностью отсортирован по возрастанию horsePower
    }

    @Test
    void shouldHandleAllOddElements() {
        // TODO: создать список только с нечётными horsePower,
        //       проверить что порядок элементов не изменился
    }

    @Test
    void shouldHandleEmptyList() {
        // TODO: передать пустой список,
        //       убедиться что не выбрасывается исключение
    }

    @Test
    void shouldHandleSingleElement() {
        // TODO: передать список из одного элемента (любой horsePower),
        //       убедиться что список не изменился
    }
}
