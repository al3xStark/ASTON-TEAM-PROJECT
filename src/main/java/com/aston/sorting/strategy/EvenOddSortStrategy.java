package com.aston.sorting.strategy;

import com.aston.sorting.model.Car;

import java.util.Comparator;
import java.util.List;

public class EvenOddSortStrategy implements SortStrategy<Car> {

    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {
        // TODO: объекты с чётным horsePower сортируются в натуральном порядке,
        //       объекты с нечётным — остаются на исходных позициях
    }
}
