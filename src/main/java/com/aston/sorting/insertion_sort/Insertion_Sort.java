package com.aston.sorting.insertion_sort;
import com.aston.sorting.model.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Insertion_Sort {
    public static void insertionSortCar(List<Car> cars, Comparator<Car> comparator) {
        for (int i = 1; i < cars.size(); i++) {
            Car key = cars.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(cars.get(j), key) > 0) {
                cars.set(j + 1, cars.get(j));
                j--;
            }
            cars.set(j + 1, key);
        }
    }

//    public static void main(String[] args) {
//        List<Car> cars = new ArrayList<>();
//        cars.add(new Car.Builder().horsePower(220).model("BMW").year(2019).build());
//        cars.add(new Car.Builder().horsePower(150).model("Audi").year(2018).build());
//        cars.add(new Car.Builder().horsePower(300).model("Mercedes").year(2022).build());
//        cars.add(new Car.Builder().horsePower(180).model("Toyota").year(2020).build());
//        cars.add(new Car.Builder().horsePower(250).model("Maserati").year(2021).build());
//        cars.add(new Car.Builder().horsePower(170).model("Hyundai").year(2016).build());
//        cars.add(new Car.Builder().horsePower(400).model("Ferrari").year(2023).build());
//        cars.add(new Car.Builder().horsePower(130).model("Kia").year(2015).build());
//        cars.add(new Car.Builder().horsePower(210).model("Subaru").year(2019).build());
//
//        // Сортировка по мощности
//        Comparator<Car> byHorsePower = (c1, c2) -> Integer.compare(c1.getHorsePower(), c2.getHorsePower());
//        Insertion_Sort.insertionSortCar(cars, byHorsePower);
//
//        System.out.println("По мощности:");
//        for (Car car : cars) {
//            System.out.println(car);
//        }
//
//        // Сортировка по модели
//        Comparator<Car> byModel = (c1, c2) -> c1.getModel().compareTo(c2.getModel());
//       Insertion_Sort.insertionSortCar(cars, byModel);
//
//        System.out.println("\nПо модели:");
//        for (Car car : cars) {
//            System.out.println(car);
//        }
//
//        // Сортировка по году
//        Comparator<Car> byYear = (c1, c2) -> Integer.compare(c1.getYear(), c2.getYear());
//        Insertion_Sort.insertionSortCar(cars, byYear);
//        System.out.println("\nПо году:");
//        for (Car car : cars) {
//            System.out.println(car);
//
//        }
//
//
//
//    }

}





