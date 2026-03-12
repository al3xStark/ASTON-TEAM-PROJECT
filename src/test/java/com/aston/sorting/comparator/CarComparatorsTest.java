package com.aston.sorting.comparator;

import com.aston.sorting.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CarComparators")
class CarComparatorsTest {

    // ------------------------------------------------------------------ вспомогательные методы

    /** Краткая обёртка над Builder'ом: создаёт Car(мощность, модель, год). */
    private static Car car(int hp, String model, int year) {
        return new Car.Builder()
                .horsePower(hp)
                .model(model)
                .year(year)
                .build();
    }

    // ------------------------------------------------------------------ BY_HORSE_POWER

    @Test
    @DisplayName("BY_HORSE_POWER: меньшая мощность — первой")
    void byHorsePower_lowerFirst() {
        Car weak = car(90,  "Lada",   2000);
        Car mid  = car(150, "Toyota", 2010);
        Car fast = car(300, "BMW",    2020);

        assertTrue(CarComparators.BY_HORSE_POWER.compare(weak, mid)  < 0);
        assertTrue(CarComparators.BY_HORSE_POWER.compare(fast, mid)  > 0);
        assertEquals(0, CarComparators.BY_HORSE_POWER.compare(mid, mid));
    }

    @Test
    @DisplayName("BY_HORSE_POWER: сортировка списка даёт возрастающий порядок")
    void byHorsePower_sortList() {
        List<Car> cars = new ArrayList<>(List.of(
                car(300, "BMW",    2020),
                car(90,  "Lada",   2000),
                car(150, "Toyota", 2010)
        ));

        cars.sort(CarComparators.BY_HORSE_POWER);

        assertEquals(90,  cars.get(0).getHorsePower());
        assertEquals(150, cars.get(1).getHorsePower());
        assertEquals(300, cars.get(2).getHorsePower());
    }

    // ------------------------------------------------------------------ BY_MODEL

    @Test
    @DisplayName("BY_MODEL: лексикографический порядок, без учёта регистра")
    void byModel_ascendingCaseInsensitive() {
        Car audi   = car(200, "Audi",   2015);
        Car bmw    = car(200, "BMW",    2015);
        Car bmwLow = car(200, "bmw",    2015);  // равно BMW без учёта регистра

        assertTrue(CarComparators.BY_MODEL.compare(audi, bmw) < 0,
                "Audi должна стоять перед BMW");
        assertEquals(0, CarComparators.BY_MODEL.compare(bmw, bmwLow),
                "bmw и BMW должны считаться равными");
    }

    @Test
    @DisplayName("BY_MODEL: сортировка списка даёт алфавитный порядок")
    void byModel_sortList() {
        List<Car> cars = new ArrayList<>(List.of(
                car(100, "Toyota", 2010),
                car(100, "Audi",   2010),
                car(100, "BMW",    2010)
        ));

        cars.sort(CarComparators.BY_MODEL);

        assertEquals("Audi",   cars.get(0).getModel());
        assertEquals("BMW",    cars.get(1).getModel());
        assertEquals("Toyota", cars.get(2).getModel());
    }

    // ------------------------------------------------------------------ BY_YEAR

    @Test
    @DisplayName("BY_YEAR: более старый год — первым")
    void byYear_olderFirst() {
        Car old     = car(100, "Ford",   1990);
        Car middle  = car(100, "Honda",  2005);
        Car modern  = car(100, "Tesla",  2023);

        assertTrue(CarComparators.BY_YEAR.compare(old, middle) < 0);
        assertTrue(CarComparators.BY_YEAR.compare(modern, old) > 0);
        assertEquals(0, CarComparators.BY_YEAR.compare(middle, middle));
    }

    @Test
    @DisplayName("BY_YEAR: сортировка списка даёт хронологический порядок")
    void byYear_sortList() {
        List<Car> cars = new ArrayList<>(List.of(
                car(100, "Tesla", 2023),
                car(100, "Ford",  1990),
                car(100, "Honda", 2005)
        ));

        cars.sort(CarComparators.BY_YEAR);

        assertEquals(1990, cars.get(0).getYear());
        assertEquals(2005, cars.get(1).getYear());
        assertEquals(2023, cars.get(2).getYear());
    }

    // ------------------------------------------------------------------ защита утилитного класса

    @Test
    @DisplayName("CarComparators нельзя инстанциировать")
    void utilityClass_noInstantiation() throws NoSuchMethodException {
        var ctor = CarComparators.class.getDeclaredConstructor();
        ctor.setAccessible(true);

        // Reflection оборачивает исключение из конструктора в InvocationTargetException.
        // Проверяем, что причиной является именно UnsupportedOperationException,
        // а не любой другой сбой.
        InvocationTargetException thrown = assertThrows(
                InvocationTargetException.class,
                ctor::newInstance
        );
        assertInstanceOf(UnsupportedOperationException.class, thrown.getCause());
    }
}
