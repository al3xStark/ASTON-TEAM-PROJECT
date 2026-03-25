package com.aston.sorting.io.output;

import com.aston.sorting.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileOutputHandler {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void writeList(List<Car> cars, String path) {
        writeList(cars, path, "Unknown");
    }

    public void writeList(List<Car> cars, String path, String sortType) {
        List<String> lines = new ArrayList<>();
        lines.add("--- " + LocalDateTime.now().format(FORMATTER) + " | Sort: " + sortType + " ---");

        for (Car car : cars) {
            lines.add(car.toString());
        }

        lines.add("");
        writeToFile(lines, path);
    }

    public void writeCount(int count, Car target, String path) {
        writeCount(count, target, path, "Unknown");
    }

    public void writeCount(int count, Car target, String path, String sortType) {
        List<String> lines = new ArrayList<>();
        lines.add("--- " + LocalDateTime.now().format(FORMATTER) + " | Sort: " + sortType + " ---");
        lines.add("Count of " + target + " = " + count);
        lines.add("");
        writeToFile(lines, path);
    }

    private void writeToFile(List<String> lines, String path) {
        try {
            Files.write(
                    Path.of(path),
                    lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
