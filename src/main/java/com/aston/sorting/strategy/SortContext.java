package com.aston.sorting.strategy;

import java.util.Comparator;
import java.util.List;

public class SortContext<T> {

    private SortStrategy<T> strategy;

    public SortContext(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void executeSort(List<T> list, Comparator<T> comparator) {
        strategy.sort(list, comparator);
    }
}
