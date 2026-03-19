package com.aston.sorting.strategy;

import com.aston.sorting.comparator.CarComparators;
import com.aston.sorting.model.Car;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Стратегия сортирует по полю horsePower:
// элементы с чётным horsePower сортируются в натуральном порядке,
// элементы с нечётным horsePower остаются на исходных позициях.
class EvenOddSortStrategyTest {

    @Test
    void evenElementsShouldBeSortedAndOddShouldRemainInPlace() {
        // Проверка 1: объекты с четными значениями horsePower должны быть упорядочены по возрастанию

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
        assertEquals(orderedCars, cars, "Результат сортировки не совпадает с ожидаемым");

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
        // Проверка: Список полностью отсортирован по возрастанию horsePower, при этом
        //           список только с чётными horsePower.

        // 1. Подготовка (создание списка со смешанными horsePower и списка с упорядоченными horsePower)
        List<Car> originalCars = Arrays.asList(
                new Car.Builder().horsePower(188).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(134).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(200).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build()
        );

        List<Car> orderedCars = Arrays.asList(
                new Car.Builder().horsePower(134).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(188).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(200).model("Audi").year(2003).build()
        );

        // 2. Действие (сортировка исходной коллекции)
        EvenOddSortStrategy sortStrategy = new EvenOddSortStrategy();
        sortStrategy.sort(originalCars, CarComparators.BY_HORSE_POWER);

        // 3. Проверка
        assertEquals(orderedCars, originalCars, "Результат сортировки не совпадает с ожидаемым");
    }

    @Test
    void shouldHandleAllOddElements() {
        // Проверка: Список не претерпевает никаких изменений после сортировки, т.к.
        // список только с нечётными horsePower.

        // 1. Подготовка (создание списка со смешанными horsePower и списка с упорядоченными horsePower).
        // Т.к. в этом тесте все объекты не попадают под критерии отбора (все нечетные), порядок объектов
        // не должен претерпеть изменений после сортировки
        List<Car> originalCars = Arrays.asList(
                new Car.Builder().horsePower(187).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(181).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(201).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(153).model("Mazda").year(2006).build()
        );

        List<Car> orderedCars = Arrays.asList(
                new Car.Builder().horsePower(187).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(181).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(201).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(153).model("Mazda").year(2006).build()
        );

        // 2. Действие (сортировка исходной коллекции)
        EvenOddSortStrategy sortStrategy = new EvenOddSortStrategy();
        sortStrategy.sort(originalCars, CarComparators.BY_HORSE_POWER);

        // 3. Проверка
        assertEquals(orderedCars, originalCars, "Результат сортировки не совпадает с ожидаемым");
    }

    @Test
    void shouldHandleEmptyList() {
        // Проверка: При передачи пустого списка не должно выбрасываться
        //           Никаких исключений.

        // 1. Подготовка (создание пустого списка)
        List<Car> originalCars = Arrays.asList();

        // 2. Действие и проверка (вызов метода сортировки)
        EvenOddSortStrategy sortStrategy = new EvenOddSortStrategy();
        assertDoesNotThrow(() -> sortStrategy.sort(originalCars, CarComparators.BY_HORSE_POWER),
                "Метод sort() выбросил исключение, хотя не должен был");
    }

    @Test
    void shouldHandleSingleElement() {
        // Проверка: При передачи списка из одного элемента (любой horsePower)
        //           не должно происходить никаких изменений после сортировки.

        // 1. Подготовка (создание двух списков "до сортировки" и "после сортировки"
        // с одинаковым одним элементом внутри)
        List<Car> originalCars = List.of(new Car.Builder().horsePower(187).model("Toyota").year(2004).build());
        List<Car> orderedCars = List.of(new Car.Builder().horsePower(187).model("Toyota").year(2004).build());

        // 2. Действие (сортировка исходной коллекции)
        EvenOddSortStrategy sortStrategy = new EvenOddSortStrategy();
        sortStrategy.sort(originalCars, CarComparators.BY_HORSE_POWER);

        // 3. Проверка
        assertEquals(orderedCars, originalCars, "Результат сортировки не совпадает с ожидаемым");
    }
}
