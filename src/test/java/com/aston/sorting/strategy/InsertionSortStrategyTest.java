package com.aston.sorting.strategy;
import com.aston.sorting.model.Car;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.aston.sorting.comparator.CarComparators;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.aston.sorting.strategy.Insertion_Sort.insertionSortCar;
import static com.aston.sorting.strategy.RandomCar.generateRandomCars;

import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortStrategyTest {

    @Test
    public void shouldSortByHorsePower() {
        // TODO: создать список Car в случайном порядке,
        //       отсортировать по horsePower, проверить ожидаемый порядок

        List<Car> randomCars = generateRandomCars(20); // создаем 20 машин
        System.out.println("Случайные машины:");
        for (Car car : randomCars) {
            System.out.println(car);
        }

        // Сортировка по мощности
        Comparator<Car> BY_HORSE_POWER = (a, b) -> Integer.compare(a.getHorsePower(), b.getHorsePower());

        new InsertionSortStrategy().sort(randomCars, BY_HORSE_POWER);


        System.out.println("По мощности:");
        for (Car car : randomCars) {
            System.out.println(car);
        }
    }

    @Test
    public void shouldSortByModel() {
        // TODO: создать список Car в случайном порядке,
        //       отсортировать по model, проверить ожидаемый порядок
        List<Car> randomCars = generateRandomCars(20); // создаем 20 машин
        System.out.println("Случайные машины:");
        for (Car car : randomCars) {
            System.out.println(car);
        }
        // Сортировка по модели
        Comparator<Car> BY_MODEL =
                (a, b) -> a.getModel().compareToIgnoreCase(b.getModel());

        new InsertionSortStrategy().sort(randomCars, BY_MODEL);
        System.out.println("\nПо модели:");
        for (Car car : randomCars) {
            System.out.println(car);
        }

    }

    @Test
    public void shouldSortByYear() {
        // TODO: создать список Car в случайном порядке,
        //       отсортировать по year, проверить ожидаемый порядок
        List<Car> randomCars = generateRandomCars(20); // создаем 20 машин
        System.out.println("Случайные машины:");
        for (Car car : randomCars) {
            System.out.println(car);
        }
        //Сортировка по году
        Comparator<Car> BY_YEAR =
                (a, b) -> Integer.compare(a.getYear(), b.getYear());

        new InsertionSortStrategy().sort(randomCars, BY_YEAR);
        System.out.println("\nПо году:");
        for (Car car : randomCars) {
            System.out.println(car);

        }
    }

    @Test
    public void shouldHandleEmptyList() {
  //      // TODO: передать пустой список, убедиться что не выбрасывается исключение
        List<Car> emptyList = new ArrayList<>(); // пустой список
        Comparator<Car> comparator = Comparator.comparingInt(Car::getHorsePower);

        try {
            new InsertionSortStrategy().sort(emptyList, comparator);

        } catch (Exception e) {
            fail("Сортировка не должна выбрасывать исключение для пустого списка, но выбросила: " + e);
        }

        assertTrue(emptyList.isEmpty(), "Пустой список должен остаться пустым после сортировки");
        System.out.println(emptyList);
    }

    @Test
    public void shouldHandleSingleElement() {
        // TODO: передать список из одного элемента, убедиться что он остался на месте

        List<Car> singleElementList = new ArrayList<>();
        Car car = new Car.Builder()
                .horsePower(150)
                .model("Model Single")
                .year(2015)
                .build();
        singleElementList.add(car);

        // Компаратор по мощности л.с.
        Comparator<Car> comparator = Comparator.comparingInt(Car::getHorsePower);

        // Для наглядности выводим объект (можно убрать)
        System.out.println(car);

        try {
            new InsertionSortStrategy().sort(singleElementList, comparator);

        } catch (Exception e) {
            fail("Сортировка не должна выбрасывать исключение для списка из одного элемента, но выбросила: " + e);
        }

        // Проверяем, что размер списка остался 1
        assertEquals(singleElementList.size(), 1, "Размер списка должен остаться 1");

        // Проверяем, что единственный элемент остался тот же самый объект (ссылка)
        assertSame(singleElementList.get(0), car, "Единственный элемент списка должен остаться на месте");
    }

    @Test
    public void shouldHandleListWithDuplicates() {
        // TODO: создать список где несколько Car имеют одинаковый horsePower,
        //       проверить что их относительный порядок не изменился после сортировки.
        List<Car> cars = new ArrayList<>();
        Car car1 = new Car.Builder().horsePower(200).model("Model A").year(2010).build();
        Car car2 = new Car.Builder().horsePower(150).model("Model B").year(2011).build();
        Car car3 = new Car.Builder().horsePower(150).model("Model C").year(2012).build();
        Car car4 = new Car.Builder().horsePower(180).model("Model D").year(2013).build();
        Car car5 = new Car.Builder().horsePower(150).model("Model E").year(2014).build();

        // Добавляем в список в таком порядке
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);

        Comparator<Car> comparator = Comparator.comparingInt(Car::getHorsePower);
        new InsertionSortStrategy().sort(cars, comparator);


        // Проверяем, что список отсортирован по horsepower
        // Мощности после сортировки должны идти по возрастанию:
        // car2(150), car3(150), car5(150), car4(180), car1(200)
        assertEquals(cars.get(0), car2);
        assertEquals(cars.get(1), car3);
        assertEquals(cars.get(2), car5);
        assertEquals(cars.get(3), car4);
        assertEquals(cars.get(4), car1);

        // Дополнительно проверяем: относительно одинаковых horsePower порядок сохранился
        // car2 идет раньше car3, car3 — раньше car5
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}










