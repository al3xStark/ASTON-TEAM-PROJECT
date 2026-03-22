package com.aston.sorting.io.input;

import com.aston.sorting.model.Car;
import com.aston.sorting.validation.CarValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FileInputHandler implements InputHandler {

    private final String filePath;

    public FileInputHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Car> read(int size) {
        try (var lines = Files.lines(Path.of(filePath))) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(this::parseLine)
                    .filter(Objects::nonNull)
                    .limit(size)
                    .toList();
        } catch (IOException e) {
            System.out.println("WARNING: Cannot read file '" + filePath + "': " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private Car parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            System.out.println("WARNING: Invalid line (expected 3 fields): " + line);
            return null;
        }

        try {
            int horsePower = Integer.parseInt(parts[0].trim());
            String model = parts[1].trim();
            int year = Integer.parseInt(parts[2].trim());

            if (!CarValidator.validateHorsePower(horsePower)) {
                System.out.println("WARNING: Invalid horsePower in line: " + line);
                return null;
            }
            if (!CarValidator.validateModel(model)) {
                System.out.println("WARNING: Invalid model in line: " + line);
                return null;
            }
            if (!CarValidator.validateYear(year)) {
                System.out.println("WARNING: Invalid year in line: " + line);
                return null;
            }

            return new Car.Builder().horsePower(horsePower).model(model).year(year).build();
        } catch (NumberFormatException e) {
            System.out.println("WARNING: Cannot parse numbers in line: " + line);
            return null;
        }
    }
}
