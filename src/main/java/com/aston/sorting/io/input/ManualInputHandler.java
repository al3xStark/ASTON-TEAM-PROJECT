package com.aston.sorting.io.input;

import com.aston.sorting.model.Car;
import com.aston.sorting.validation.CarValidator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualInputHandler implements InputHandler {
    private final Scanner scanner;


    public ManualInputHandler(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public ManualInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public List<Car> read(int size) {
        List<Car> cars = new ArrayList<>(size);
        int year;
        int horsePower;
        String model;

        while(size > 0) {
            System.out.println("Введите мощность");
            horsePower = scanner.nextInt();

            while (! CarValidator.validateHorsePower(horsePower)) {
                System.out.printf("Ошибка: мощность не может быть меньше или равной 0 (мощность = %d)."
                        + " Введите мощность: \n", horsePower);
                horsePower = scanner.nextInt();
            }
            scanner.nextLine();

            System.out.println("Введите модель: ");
            model = scanner.nextLine();

            while (! CarValidator.validateModel(model)) {
                System.out.printf("Ошибка: значение не может быть пустым (модель = %s)  Введите модель: \n", model);
                model = scanner.nextLine();
            }

            System.out.println("Введите год: ");
            year = scanner.nextInt();

            while (! CarValidator.validateYear(year)) {
                System.out.printf("Ошибка: год должен быть между 1896 и текущим (год = %d). Введите год: \n", year);
                year= scanner.nextInt();
            }


            Car car = new Car.Builder().model(model).year(year).horsePower(horsePower).build();
            cars.add(car);
            size -=1;
        }
        return cars;
    }
}
