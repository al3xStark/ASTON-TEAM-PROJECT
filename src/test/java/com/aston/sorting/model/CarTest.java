package com.aston.sorting.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Car model tests")
class CarTest {

    private static final int CURRENT_YEAR = Year.now().getValue();
    private static final int MIN_YEAR = 1886;

    // ================================================================ Builder

    @Test
    @DisplayName("Builder: успешная сборка — геттеры возвращают переданные значения")
    void builder_validValues_createsCar() {
        Car car = new Car.Builder()
                .horsePower(200)
                .model("BMW M3")
                .year(2022)
                .build();

        assertAll(
                () -> assertEquals(200, car.getHorsePower()),
                () -> assertEquals("BMW M3", car.getModel()),
                () -> assertEquals(2022, car.getYear())
        );
    }

    // ---- horsePower

    @Test
    @DisplayName("Builder: horsePower = 1 — граничное допустимое значение")
    void builder_horsePowerOne_ok() {
        Car car = new Car.Builder().horsePower(1).model("Mini").year(2000).build();
        assertEquals(1, car.getHorsePower());
    }

    @ParameterizedTest(name = "horsePower={0} должен выбросить исключение")
    @ValueSource(ints = {0, -1, -100})
    @DisplayName("Builder: horsePower <= 0 → IllegalStateException")
    void builder_invalidHorsePower_throws(int hp) {
        Car.Builder builder = new Car.Builder().horsePower(hp).model("Test").year(2000);
        assertThrows(IllegalStateException.class, builder::build);
    }

    // ---- model

    @Test
    @DisplayName("Builder: model = null → IllegalStateException")
    void builder_nullModel_throws() {
        Car.Builder builder = new Car.Builder().horsePower(100).model(null).year(2000);
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    @DisplayName("Builder: model = пустая строка → IllegalStateException")
    void builder_emptyModel_throws() {
        Car.Builder builder = new Car.Builder().horsePower(100).model("").year(2000);
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    @DisplayName("Builder: model = строка из пробелов → IllegalStateException")
    void builder_blankModel_throws() {
        Car.Builder builder = new Car.Builder().horsePower(100).model("   ").year(2000);
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    @DisplayName("Builder: model с ведущими/хвостовыми пробелами — фиксируем поведение")
    void builder_modelWithSurroundingSpaces_behaviorIsExplicit() {
        // isBlank() вернёт false, поэтому Builder сейчас принимает " Toyota ".
        // Если принято решение тримить — поменять assertDoesNotThrow на assertThrows
        // и добавить trim() в Builder.
        Car car = assertDoesNotThrow(() ->
                new Car.Builder().horsePower(100).model(" Toyota ").year(2000).build()
        );
        // Пока пробелы сохраняются как есть — фиксируем это явно.
        assertEquals(" Toyota ", car.getModel());
    }

    // ---- year

    @Test
    @DisplayName("Builder: year = MIN_YEAR (1886) — граничное допустимое значение")
    void builder_yearAtMinBound_ok() {
        Car car = new Car.Builder().horsePower(10).model("Benz Patent").year(MIN_YEAR).build();
        assertEquals(MIN_YEAR, car.getYear());
    }

    @Test
    @DisplayName("Builder: year = текущий год — граничное допустимое значение")
    void builder_yearAtCurrentYear_ok() {
        Car car = new Car.Builder().horsePower(300).model("Model S").year(CURRENT_YEAR).build();
        assertEquals(CURRENT_YEAR, car.getYear());
    }

    @ParameterizedTest(name = "year={0} должен выбросить исключение")
    @ValueSource(ints = {1885, 0, -1})
    @DisplayName("Builder: year < 1886 → IllegalStateException")
    void builder_yearBelowMin_throws(int year) {
        Car.Builder builder = new Car.Builder().horsePower(100).model("Test").year(year);
        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    @DisplayName("Builder: year > текущий год → IllegalStateException")
    void builder_yearAboveMax_throws() {
        Car.Builder builder = new Car.Builder()
                .horsePower(100).model("FutureCar").year(CURRENT_YEAR + 1);
        assertThrows(IllegalStateException.class, builder::build);
    }

    // ================================================================ equals

    @Test
    @DisplayName("equals: одинаковые поля → true")
    void equals_sameFields_true() {
        Car a = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        Car b = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        assertEquals(a, b);
    }

    @Test
    @DisplayName("equals: разные horsePower → false")
    void equals_differentHorsePower_false() {
        Car a = new Car.Builder().horsePower(100).model("Toyota").year(2020).build();
        Car b = new Car.Builder().horsePower(200).model("Toyota").year(2020).build();
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("equals: разные model → false")
    void equals_differentModel_false() {
        Car a = new Car.Builder().horsePower(150).model("Honda").year(2020).build();
        Car b = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("equals: разные year → false")
    void equals_differentYear_false() {
        Car a = new Car.Builder().horsePower(150).model("Toyota").year(2019).build();
        Car b = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        assertNotEquals(a, b);
    }

    @Test
    @DisplayName("equals: сравнение с null → false")
    void equals_null_false() {
        Car car = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        assertNotEquals(car, null);
    }

    @Test
    @DisplayName("equals: сравнение с объектом другого типа → false")
    void equals_differentType_false() {
        Car car = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        assertNotEquals(car, "not a car");
    }

    // ================================================================ hashCode

    @Test
    @DisplayName("hashCode: равные объекты имеют одинаковый hashCode")
    void hashCode_equalObjects_sameHash() {
        Car a = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        Car b = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        assertEquals(a.hashCode(), b.hashCode());
    }

    // ================================================================ toString

    @Test
    @DisplayName("toString: содержит все три поля")
    void toString_containsAllFields() {
        Car car = new Car.Builder().horsePower(250).model("Mustang").year(2021).build();
        String str = car.toString();

        assertAll(
                () -> assertTrue(str.contains("250"),     "horsePower отсутствует в toString"),
                () -> assertTrue(str.contains("Mustang"), "model отсутствует в toString"),
                () -> assertTrue(str.contains("2021"),    "year отсутствует в toString")
        );
    }

    @Test
    @DisplayName("toString: не возвращает null и не пустой")
    void toString_notNullOrEmpty() {
        Car car = new Car.Builder().horsePower(150).model("Toyota").year(2020).build();
        assertNotNull(car.toString());
        assertFalse(car.toString().isBlank());
    }
}