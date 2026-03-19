package com.aston.sorting.io;

import com.aston.sorting.io.output.FileOutputHandler;
import com.aston.sorting.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileOutputHandlerTest {

    private static Car car(int hp, String model, int year) {
        return new Car.Builder().horsePower(hp).model(model).year(year).build();
    }

    @Test
    void writeListShouldAppendToFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("output.txt");
        FileOutputHandler handler = new FileOutputHandler();

        handler.writeList(List.of(car(150, "Toyota", 2020)), file.toString());
        handler.writeList(List.of(car(200, "BMW", 2022)), file.toString());

        String content = Files.readString(file);
        assertTrue(content.contains("Toyota"));
        assertTrue(content.contains("BMW"));
    }

    @Test
    void writeCountShouldAppendToFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("output.txt");
        FileOutputHandler handler = new FileOutputHandler();
        Car target = car(100, "Honda", 2019);

        handler.writeCount(3, target, file.toString());
        handler.writeCount(1, target, file.toString());

        String content = Files.readString(file);
        assertEquals(2, content.lines().filter(l -> l.startsWith("Count of")).count());
    }

    @Test
    void writeListShouldIncludeHeaderWithSortType(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("output.txt");
        FileOutputHandler handler = new FileOutputHandler();

        handler.writeList(List.of(car(100, "Lada", 2000)), file.toString(), "InsertionSort");

        String content = Files.readString(file);
        assertTrue(content.contains("InsertionSort"));
    }

    @Test
    void writeListShouldCreateFileIfNotExists(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("new_file.txt");
        FileOutputHandler handler = new FileOutputHandler();

        assertFalse(Files.exists(file));
        handler.writeList(List.of(car(90, "Kia", 2015)), file.toString());
        assertTrue(Files.exists(file));
    }

    @Test
    void writeListWithEmptyListShouldWriteOnlyHeader(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("output.txt");
        FileOutputHandler handler = new FileOutputHandler();

        handler.writeList(List.of(), file.toString(), "MergeSort");

        String content = Files.readString(file);
        assertTrue(content.contains("MergeSort"));
        assertFalse(content.contains("Car{"));
    }
}
