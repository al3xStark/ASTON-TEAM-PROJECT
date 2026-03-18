package com.aston.sorting.strategy;

import com.aston.sorting.comparator.CarComparators;
import com.aston.sorting.model.Car;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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

        // 1. Подготовка (создание списка со смешанными horsePower и списка с упорядоченными horsePower)
        List<Car> originalCars = Arrays.asList(
                new Car.Builder().horsePower(189).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(200).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build()
        );

        List<Car> orderedCars = Arrays.asList(
                new Car.Builder().horsePower(189).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(200).model("Audi").year(2003).build()
        );

        // Создание копии списка с упорядоченными horsePower
        // (оригинальный список пригодится для проверки неизменяемости расположения
        // объектов с нечетным horsePower)
        List<Car> cars = new ArrayList<>(originalCars);

        // 2. Действие (сортировка исходной коллекции)
        EvenOddSortStrategy sortStrategy = new EvenOddSortStrategy();
        sortStrategy.sort(cars, CarComparators.BY_HORSE_POWER);

        // 3. Проверка

        // Находим индексы чётных элементов в исходном списке
        List<Integer> evenIndices = new ArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getHorsePower() % 2 == 0) {
                evenIndices.add(i);
            }
        }

        // Получаем чётные элементы из исходного списка
        List<Car> originalEvens = evenIndices.stream()
                .map(cars::get)
                .toList();

        // Получаем чётные элементы из результирующего списка
        List<Car> resultEvens = evenIndices.stream()
                .map(orderedCars::get)
                .toList();

        // Сортируем оригинальные чётные (ожидаемый результат)
        List<Car> expectedEvens = originalEvens.stream()
                .sorted(CarComparators.BY_HORSE_POWER)
                .toList();

        // Проверка 1: Чётные отсортированы
        assertEquals(expectedEvens, resultEvens,
                "Чётные элементы должны быть отсортированы");

        // Проверка 2: Нечётные на своих местах
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getHorsePower() % 2 != 0) {
                assertSame(originalCars.get(i), cars.get(i),
                        "Нечётный элемент на позиции " + i + " не должен меняться");
            }
        }
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
