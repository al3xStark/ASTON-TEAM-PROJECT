package com.aston.sorting.strategy;
import com.aston.sorting.model.Car;
import java.util.Comparator;
import java.util.List;

public class InsertionSortStrategy implements SortStrategy<Car> {

    @Override
    public void sort(List<Car> cars, Comparator<Car> comparator) {
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
}



