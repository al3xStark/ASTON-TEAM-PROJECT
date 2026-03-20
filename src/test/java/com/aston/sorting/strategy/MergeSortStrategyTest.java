package com.aston.sorting.strategy;

import com.aston.sorting.comparator.CarComparators;
import com.aston.sorting.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeSortStrategyTest {

    private MergeSortStrategy mergeSortStrategy;

    @BeforeEach
    void setUp() {
        mergeSortStrategy = new MergeSortStrategy();
    }

    @Test
    void shouldSortByHorsePower() {
        // Проверка: Список полностью отсортирован по возрастанию horsePower

        // 1. Подготовка (создание списка со смешанными horsePower и списка с упорядоченными horsePower)
        List<Car> originalCars = Arrays.asList(
                new Car.Builder().horsePower(189).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(200).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build()
        );

        List<Car> orderedCars = Arrays.asList(
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(189).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(200).model("Audi").year(2003).build()
        );

        // 2. Действие (сортировка исходной коллекции)
        mergeSortStrategy.sort(originalCars, CarComparators.BY_HORSE_POWER);

        // 3. Проверка
        assertEquals(orderedCars, originalCars, "Результат сортировки не совпадает с ожидаемым");
    }

    @Test
    void shouldSortByModel() {
        // Проверка: Список полностью отсортирован по возрастанию model

        // 1. Подготовка (создание списка со смешанными horsePower и списка с упорядоченными horsePower)
        List<Car> originalCars = Arrays.asList(
                new Car.Builder().horsePower(189).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(200).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build()
        );

        List<Car> orderedCars = Arrays.asList(
                new Car.Builder().horsePower(200).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build(),
                new Car.Builder().horsePower(189).model("Toyota").year(2004).build()
        );

        // 2. Действие (сортировка исходной коллекции)
        mergeSortStrategy.sort(originalCars, CarComparators.BY_MODEL);

        // 3. Проверка
        assertEquals(orderedCars, originalCars, "Результат сортировки не совпадает с ожидаемым");
    }

    @Test
    void shouldSortByYear() {
        // Проверка: Список полностью отсортирован по возрастанию year

        // 1. Подготовка (создание списка со смешанными horsePower и списка с упорядоченными horsePower)
        List<Car> originalCars = Arrays.asList(
                new Car.Builder().horsePower(189).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(200).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build()
        );

        List<Car> orderedCars = Arrays.asList(
                new Car.Builder().horsePower(200).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(189).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(180).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(150).model("Mazda").year(2006).build(),
                new Car.Builder().horsePower(135).model("BMW").year(2007).build()
        );

        // 2. Действие (сортировка исходной коллекции)
        mergeSortStrategy.sort(originalCars, CarComparators.BY_YEAR);

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
        MergeSortStrategy mergeSortStrategy = new MergeSortStrategy();
        assertDoesNotThrow(() -> mergeSortStrategy.sort(originalCars, CarComparators.BY_HORSE_POWER),
                "Метод sort() выбросил исключение, хотя не должен был");
    }

    @Test
    void shouldHandleSingleElement() {
        // Проверка: При передачи списка из одного элемента
        //           не должно происходить никаких изменений после сортировки.

        // 1. Подготовка (создание двух списков "до сортировки" и "после сортировки"
        // с одинаковым одним элементом внутри)
        List<Car> originalCars = new ArrayList<>(List.of(new Car.Builder().horsePower(187).model("Toyota").year(2004).build()));
        List<Car> orderedCars = new ArrayList<>(List.of(new Car.Builder().horsePower(187).model("Toyota").year(2004).build()));

        // 2. Действие (сортировка исходной коллекции)
        mergeSortStrategy.sort(originalCars, CarComparators.BY_YEAR);

        // 3. Проверка
        assertEquals(orderedCars, originalCars, "Результат сортировки не совпадает с ожидаемым");
    }

    @Test
    void shouldHandleListWithDuplicates() {
        // Проверка: Список не претерпевает никаких изменений после сортировки, т.к.
        // список где все Car имеют одинаковый horsePower.

        // 1. Подготовка (создание списка со смешанными horsePower и списка с упорядоченными horsePower).
        // Т.к. в этом тесте все объекты не попадают под критерии отбора (все horsePower равны), порядок объектов
        // не должен претерпеть изменений после сортировки
        List<Car> originalCars = Arrays.asList(
                new Car.Builder().horsePower(201).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(201).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(201).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(201).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(201).model("Mazda").year(2006).build()
        );

        List<Car> orderedCars = Arrays.asList(
                new Car.Builder().horsePower(201).model("Toyota").year(2004).build(),
                new Car.Builder().horsePower(201).model("Honda").year(2006).build(),
                new Car.Builder().horsePower(201).model("BMW").year(2007).build(),
                new Car.Builder().horsePower(201).model("Audi").year(2003).build(),
                new Car.Builder().horsePower(201).model("Mazda").year(2006).build()
        );

        // 2. Действие (сортировка исходной коллекции)
        mergeSortStrategy.sort(originalCars, CarComparators.BY_HORSE_POWER);

        // 3. Проверка
        assertEquals(orderedCars, originalCars, "Результат сортировки не совпадает с ожидаемым");
    }
}
