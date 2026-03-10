package com.aston.sorting.concurrent;

import com.aston.sorting.model.Car;

import java.util.List;

public class ConcurrentCounter {

    /**
     * Многопоточно подсчитывает количество вхождений target в список.
     * Список разбивается на части по числу (какому числу?),
     * каждая часть обрабатывается в отдельном Callable<Integer>.
     *
     * @param list   список для поиска
     * @param target искомый элемент
     * @return количество вхождений
     */
    public int countOccurrences(List<Car> list, Car target) {
        // TODO: реализовать многопоточный подсчёт через ExecutorService + Callable
        return 0;
    }
}
