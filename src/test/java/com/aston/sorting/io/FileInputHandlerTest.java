package com.aston.sorting.io;

import com.aston.sorting.io.input.FileInputHandler;
import com.aston.sorting.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileInputHandlerTest {

    @Test
    void shouldReadValidCarsFromFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("cars.csv");
        Files.writeString(file, "150,Toyota,2020\n200,BMW,2018\n");

        FileInputHandler handler = new FileInputHandler(file.toString());
        List<Car> cars = handler.read(0);

        assertEquals(2, cars.size());
        assertEquals(150, cars.get(0).getHorsePower());
        assertEquals("Toyota", cars.get(0).getModel());
        assertEquals(2020, cars.get(0).getYear());
        assertEquals(200, cars.get(1).getHorsePower());
        assertEquals("BMW", cars.get(1).getModel());
        assertEquals(2018, cars.get(1).getYear());
    }

    @Test
    void shouldSkipInvalidLines(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("cars.csv");
        // -5 horsePower invalid, year 1800 < 1886 invalid, empty model invalid
        Files.writeString(file, "150,Toyota,2020\n-5,BMW,2018\n200,Honda,1800\n100,,2010\n");

        FileInputHandler handler = new FileInputHandler(file.toString());
        List<Car> cars = handler.read(0);

        assertEquals(1, cars.size());
        assertEquals("Toyota", cars.get(0).getModel());
    }

    @Test
    void shouldReturnEmptyListForEmptyFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("cars.csv");
        Files.writeString(file, "");

        FileInputHandler handler = new FileInputHandler(file.toString());
        List<Car> cars = handler.read(0);

        assertTrue(cars.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenAllLinesInvalid(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("cars.csv");
        Files.writeString(file, "-1,BMW,2020\n0,Toyota,2019\n100,Honda,1800\n");

        FileInputHandler handler = new FileInputHandler(file.toString());
        List<Car> cars = handler.read(0);

        assertTrue(cars.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenFileNotFound() {
        FileInputHandler handler = new FileInputHandler("nonexistent/path/cars.csv");

        List<Car> cars = handler.read(0);

        assertTrue(cars.isEmpty());
    }

    @Test
    void shouldSkipMalformedLines(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("cars.csv");
        // wrong number of fields and non-numeric values
        Files.writeString(file, "notANumber,Toyota,2020\n150,BMW\n100,Honda,2010\n");

        FileInputHandler handler = new FileInputHandler(file.toString());
        List<Car> cars = handler.read(0);

        assertEquals(1, cars.size());
        assertEquals("Honda", cars.get(0).getModel());
    }
}
