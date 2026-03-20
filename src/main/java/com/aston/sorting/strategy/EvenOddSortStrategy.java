package com.aston.sorting.strategy;

import com.aston.sorting.model.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 Объекты с чётным horsePower сортируются в натуральном порядке,
 объекты с нечётным — остаются на исходных позициях.
 */
public class EvenOddSortStrategy implements SortStrategy<Car> {

    private SortStrategy<Car> innerStrategy;

    public EvenOddSortStrategy(SortStrategy<Car> innerStrategy) {
        if (innerStrategy == null) {
            throw new IllegalArgumentException("innerStrategy cannot be null");
        }
        this.innerStrategy = innerStrategy;
    }

    public void setInnerStrategy (SortStrategy<Car> innerStrategy) {
        if (innerStrategy == null) {
            throw new IllegalArgumentException("innerStrategy cannot be null");
        }
        this.innerStrategy = innerStrategy;
    }

    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }

        int[] evenIndices = IntStream.range(0, list.size())
                .filter(i -> list.get(i).getHorsePower() % 2 == 0)
                .toArray();

        List<Car> evenCars = new ArrayList<>();
        for (int index : evenIndices) {
            evenCars.add(list.get(index));
        }

        innerStrategy.sort(evenCars, comparator);


        for (int i = 0; i < evenIndices.length; i++) {
            list.set(evenIndices[i], evenCars.get(i));
        }
    }
}
