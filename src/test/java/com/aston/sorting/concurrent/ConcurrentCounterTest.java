package com.aston.sorting.concurrent;

import com.aston.sorting.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ConcurrentCounterTest {
    ConcurrentCounter counter;

    @BeforeEach
    void setUp() {
        counter = new ConcurrentCounter();
    }

    @Test
    void shouldReturnCorrectCount() {
        Car car = new Car.Builder().horsePower(120).model("honda").year(2026).build();
        Car car1 = new Car.Builder().horsePower(90).model("lada").year(2020).build();
        Car car2 = new Car.Builder().horsePower(70).model("toyta").year(2010).build();
        Car car3 = new Car.Builder().horsePower(500).model("niva").year(2000).build();
        Car car4 = new Car.Builder().horsePower(1300).model("kraz").year(1990).build();
        List<Car> cars = List.of(car, car1, car2, car3, car4, car, car,car);


        int number = counter.countOccurrences(cars, car);

        assertEquals(4, number);
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        Car car = new Car.Builder().horsePower(120).model("honda").year(2026).build();
        List<Car> cars = new ArrayList<>();

        int number = counter.countOccurrences(cars, car);

        assertEquals(0, number);
    }

    @Test
    void shouldReturnZeroWhenNoMatches() {
        Car car = new Car.Builder().horsePower(120).model("honda").year(2026).build();
        Car car1 = new Car.Builder().horsePower(90).model("lada").year(2020).build();
        Car car2 = new Car.Builder().horsePower(70).model("toyta").year(2010).build();
        Car car3 = new Car.Builder().horsePower(500).model("niva").year(2000).build();
        Car car4 = new Car.Builder().horsePower(1300).model("kraz").year(1990).build();
        List<Car> cars = List.of(car1, car1, car2, car3, car4, car2, car3,car4);

        int number = counter.countOccurrences(cars, car);

        assertEquals(0, number);
    }

    @Test
    void shouldReturnSizeWhenAllElementsMatch() {
        Car car = new Car.Builder().horsePower(120).model("honda").year(2026).build();
        List<Car> cars = List.of(car, car, car, car, car, car);

        int number = counter.countOccurrences(cars, car);

        assertEquals(cars.size(), number);
    }

    @Test
    void shouldReturnCorrectCountForLargeList() {
        Car car = new Car.Builder().horsePower(120).model("honda").year(2026).build();
        Car car1 = new Car.Builder().horsePower(90).model("lada").year(2020).build();
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            if (i < 3000) {
                cars.add(car);
            }  else {
                cars.add(car1);
            }
        }

        int number = counter.countOccurrences(cars, car);

        assertEquals(3000, number);
    }
}
