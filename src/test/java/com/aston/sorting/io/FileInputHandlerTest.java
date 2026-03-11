package com.aston.sorting.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

class FileInputHandlerTest {

    // Используй @TempDir для создания временных файлов — JUnit 5 сам создаёт
    // и удаляет временную директорию после каждого теста.
    //
    // Пример создания тестового файла:
    //   Path file = tempDir.resolve("cars.csv");
    //   Files.writeString(file, "150,Toyota,2020\n200,BMW,2018\n");
    //   FileInputHandler handler = new FileInputHandler(file.toString());
    //
    // Формат строки в CSV: horsePower,model,year

    @Test
    void shouldReadValidCarsFromFile(@TempDir Path tempDir) {
        // TODO: создать CSV-файл с несколькими валидными строками,
        //       вызвать read(n),
        //       проверить что все Car прочитаны и поля совпадают с ожидаемыми
    }

    @Test
    void shouldSkipInvalidLines(@TempDir Path tempDir) {
        // TODO: создать CSV-файл с валидными и невалидными строками
        //       (например: отрицательный horsePower, год до 1886, пустая модель),
        //       проверить что невалидные строки пропущены, а валидные — прочитаны
    }

    @Test
    void shouldReturnEmptyListForEmptyFile(@TempDir Path tempDir) {
        // TODO: создать пустой файл,
        //       проверить что возвращается пустой список
    }

    @Test
    void shouldReturnEmptyListWhenAllLinesInvalid(@TempDir Path tempDir) {
        // TODO: файл только с невалидными строками,
        //       проверить что возвращается пустой список (не исключение)
    }
}
