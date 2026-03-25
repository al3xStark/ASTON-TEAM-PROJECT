package com.aston.sorting.io.input;
import com.aston.sorting.model.Car;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class RandomInputHandler implements com.aston.sorting.io.input.InputHandler {
    private static final String[] MODELS = {
            "BMW", "Audi", "Mercedes", "Toyota", "Honda",
            "Maserati", "Hyundai", "Ferrari", "Kia", "Subaru"
    };
    private static final int MIN_YEAR = 1886;
    private static final int MAX_YEAR = java.time.Year.now().getValue();
    private static final Random rand = new Random();


    @Override
    public List<Car> read(int size) {

            return Stream.generate(() -> new Car.Builder()
                            .model(MODELS[rand.nextInt(MODELS.length)])
                            .horsePower(rand.nextInt(301) + 100) // 100-400 л.с.
                            .year(rand.nextInt(MAX_YEAR - MIN_YEAR + 1) + MIN_YEAR)
                            .build())
                            .limit(size)
                            .toList();

    }
}
