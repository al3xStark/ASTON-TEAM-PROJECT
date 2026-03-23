
package com.aston.sorting.io.input;

import com.aston.sorting.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomInputHandler implements com.aston.sorting.io.input.InputHandler {

    @Override
    public List<Car> read(int size) {

            String[] models = {"BMW", "Audi", "Mercedes", "Toyota", "Honda", "Maserati", "Hyundai", "Ferrari", "Kia", "Subaru"};
            int minYear = 1886;
            int maxYear = java.time.Year.now().getValue();
            List<Car> cars = new ArrayList<>();
            Random rand = new Random();

            return Stream.generate(() -> new Car.Builder()
                            .model(models[rand.nextInt(models.length)])
                            .horsePower(rand.nextInt(301) + 100) // 100-400 л.с.
                            .year(rand.nextInt(maxYear - minYear + 1) + minYear)
                            .build())
                    .limit(size)
                    .collect(Collectors.toList());

    }
}
