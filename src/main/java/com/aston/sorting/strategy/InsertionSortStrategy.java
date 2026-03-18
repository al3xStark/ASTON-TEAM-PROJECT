package com.aston.sorting.strategy;

import com.aston.sorting.model.Car;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InsertionSortStrategy<Car> implements com.aston.sorting.strategy.SortStrategy<com.aston.sorting.model.Car> {

      // TODO: реализовать алгоритм сортировки вставками


    @Override
    public void sort(List<com.aston.sorting.model.Car> cars, Comparator<com.aston.sorting.model.Car> comparator) {
        for (int i = 1; i < cars.size(); i++) {
            com.aston.sorting.model.Car key = cars.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(cars.get(j), key) > 0) {
                cars.set(j + 1, cars.get(j));
                j--;
            }
            cars.set(j + 1, key);
        }
    }
}



