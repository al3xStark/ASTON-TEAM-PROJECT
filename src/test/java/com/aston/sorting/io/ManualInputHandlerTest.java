package com.aston.sorting.io;

import org.junit.jupiter.api.Test;

class ManualInputHandlerTest {

    // ManualInputHandler должен принимать InputStream в конструкторе,
    // чтобы в тестах можно было подменить System.in на заготовленный ввод.
    //
    // Пример подмены ввода:
    //   String input = "150\nToyota\n2020\n";
    //   InputStream stream = new ByteArrayInputStream(input.getBytes());
    //   ManualInputHandler handler = new ManualInputHandler(stream);
    //
    // Каждая строка — отдельный "Enter" пользователя.
    // Порядок полей при вводе: horsePower → model → year

    @Test
    void shouldCreateCarFromValidInput() {
        // TODO: собрать строку с валидными значениями (horsePower, model, year),
        //       обернуть в ByteArrayInputStream, передать в конструктор ManualInputHandler,
        //       вызвать read(1), проверить что Car создан с правильными полями
    }

    @Test
    void shouldRetryOnInvalidHorsePower() {
        // TODO: первой строкой передать невалидный horsePower (например, -1 или "abc"),
        //       второй — валидный, затем model и year,
        //       проверить что Car всё равно создан с правильным horsePower
    }

    @Test
    void shouldRetryOnInvalidYear() {
        // TODO: передать валидный horsePower и model,
        //       первым годом — невалидный (например, 1800), затем — валидный,
        //       проверить что Car создан с правильным year
    }

    @Test
    void shouldRetryOnEmptyModel() {
        // TODO: передать валидный horsePower,
        //       первой строкой модели — пустую строку, затем — валидную,
        //       проверить что Car создан с правильной model
    }

    @Test
    void shouldCreateMultipleCarsFromValidInput() {
        // TODO: передать ввод для 2 Car подряд, вызвать read(2),
        //       проверить что оба Car созданы корректно
}
}
