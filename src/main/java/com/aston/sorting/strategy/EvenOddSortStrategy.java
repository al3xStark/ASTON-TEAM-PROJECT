package com.aston.sorting.strategy;

import com.aston.sorting.model.Car;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class EvenOddSortStrategy implements SortStrategy<Car> {

    /**
     Объекты с чётным horsePower сортируются в натуральном порядке,
     объекты с нечётным — остаются на исходных позициях.
     */
    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {
        // TODO: объекты с чётным horsePower сортируются в натуральном порядке,
        //       объекты с нечётным — остаются на исходных позициях
        if (list == null || list.size() <= 1) {
            return;
        }

        int[] evenIndices = IntStream.range(0, list.size())
                .filter(i -> list.get(i).getHorsePower() % 2 == 0)
                .toArray();

        List<Car> evenCars = Arrays.stream(evenIndices)
                .mapToObj(list::get)
                .sorted(comparator)
                .toList();

        for (int i = 0; i < evenIndices.length; i++) {
            list.set(evenIndices[i], evenCars.get(i));
        }
    }
}
