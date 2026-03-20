package com.aston.sorting.strategy;

import com.aston.sorting.model.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortStrategy implements SortStrategy<Car> {

    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {

        if (list == null || list.size() <= 1) {
            return;
        }

        int n = list.size();
        int mid = n / 2;

        List<Car> list1 = new ArrayList<>(list.subList(0, mid));
        List<Car> list2 = new ArrayList<>(list.subList(mid, n));

        sort(list1,  comparator);
        sort(list2,  comparator);
        merge(list, list1, list2,  comparator);
    }

    private static void merge(List<Car> list, List<Car> leftList, List<Car> rightList, Comparator<Car> comparator) {

        int leftSize = leftList.size();
        int rightSize = rightList.size();
        int i = 0;
        int j = 0;
        int idx = 0;

        while (i < leftSize && j < rightSize) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
                list.set(idx++, leftList.get(i++));
            }
            else  {
                list.set(idx++, rightList.get(j++));
            }
        }

        for (int ll = i; ll < leftSize; ll++) {
            list.set(idx++, leftList.get(ll));
        }
        for (int rr = j; rr < rightSize; rr++) {
            list.set(idx++, rightList.get(rr));
        }

    }
}
