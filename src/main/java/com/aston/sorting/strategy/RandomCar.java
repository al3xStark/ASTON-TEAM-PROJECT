package com.aston.sorting.strategy;

import com.aston.sorting.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCar {
    // Метод для генерации случайных машин

    static List<Car> generateRandomCars(int count) {
        String[] models = {"BMW", "Audi", "Mercedes", "Toyota", "Honda", "Maserati", "Hyundai", "Ferrari", "Kia", "Subaru"};
        int minYear = 1920;
        int maxYear = 2023;
        List<Car> cars = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            String model = models[rand.nextInt(models.length)];
            int horsePower = rand.nextInt(301) + 100; // 100-400 л.с.
            int year = rand.nextInt(maxYear - minYear + 1) + minYear;
            cars.add(new Car.Builder()
                    .model(model)
                    .horsePower(horsePower)
                    .year(year)
                    .build());
        }
        return cars;
    }

//    public static void main(String[] args) {
//        List<Car> randomCars = generateRandomCars(20); // создаем 20 машин
//        System.out.println("Случайные машины:");
//        for (Car car : randomCars) {
//            System.out.println(car);
//        }
//    }
}
