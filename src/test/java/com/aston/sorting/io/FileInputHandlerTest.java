package com.aston.sorting.io;

import com.aston.sorting.io.input.FileInputHandler;
import com.aston.sorting.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileInputHandlerTest {

    @TempDir
    Path tempDir;

    private FileInputHandler handler;
    private Path file;

    @BeforeEach
    void setUp() {
        file = tempDir.resolve("cars.csv");
        handler = new FileInputHandler(file.toString());
    }

    @Test
    void shouldReadValidCarsFromFile() throws IOException {
        Files.writeString(file, "150,Toyota,2020\n200,BMW,2018\n");

        List<Car> cars = handler.read(Integer.MAX_VALUE);

        assertEquals(2, cars.size());
        assertAll(
                () -> assertEquals(150, cars.get(0).getHorsePower()),
                () -> assertEquals("Toyota", cars.get(0).getModel()),
                () -> assertEquals(2020, cars.get(0).getYear())
        );
        assertAll(
                () -> assertEquals(200, cars.get(1).getHorsePower()),
                () -> assertEquals("BMW", cars.get(1).getModel()),
                () -> assertEquals(2018, cars.get(1).getYear())
        );
    }

    @Test
    void shouldSkipInvalidLines() throws IOException {
        // -5 horsePower invalid, year 1800 < 1886 invalid, empty model invalid
        Files.writeString(file, "150,Toyota,2020\n-5,BMW,2018\n200,Honda,1800\n100,,2010\n");

        List<Car> cars = handler.read(Integer.MAX_VALUE);

        assertEquals(1, cars.size());
        assertEquals("Toyota", cars.get(0).getModel());
    }

    @Test
    void shouldReturnEmptyListForEmptyFile() throws IOException {
        Files.writeString(file, "");

        List<Car> cars = handler.read(Integer.MAX_VALUE);

        assertTrue(cars.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenAllLinesInvalid() throws IOException {
        Files.writeString(file, "-1,BMW,2020\n0,Toyota,2019\n100,Honda,1800\n");

        List<Car> cars = handler.read(Integer.MAX_VALUE);

        assertTrue(cars.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenFileNotFound() {
        FileInputHandler localHandler = new FileInputHandler("nonexistent/path/cars.csv");

        List<Car> cars = localHandler.read(Integer.MAX_VALUE);

        assertTrue(cars.isEmpty());
    }

    @Test
    void shouldSkipMalformedLines() throws IOException {
        // wrong number of fields and non-numeric values
        Files.writeString(file, "notANumber,Toyota,2020\n150,BMW\n100,Honda,2010\n");

        List<Car> cars = handler.read(Integer.MAX_VALUE);

        assertEquals(1, cars.size());
        assertEquals("Honda", cars.get(0).getModel());
    }
}
