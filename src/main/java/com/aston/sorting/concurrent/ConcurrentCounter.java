package com.aston.sorting.concurrent;

import com.aston.sorting.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

public class ConcurrentCounter {

    public int countOccurrences(List<Car> list, Car target) {
        if (list == null || list.isEmpty()) return 0;
        int processorsAvailable = Runtime.getRuntime().availableProcessors();
        int threadsNum = Math.min(list.size(), processorsAvailable);
        int chunkSize = (int) Math.ceil((double) list.size() / threadsNum);



        try (ExecutorService executor = Executors.newFixedThreadPool(threadsNum)) {
            List<Callable<Integer>> tasks = new ArrayList<>();

            for (int thread = 0; thread < threadsNum; thread++) {
                int start = thread * chunkSize;
                int end = Math.min(start + chunkSize, list.size());
                if (start >= end) break;

                tasks.add(() -> {
                    int count = 0;
                    for (int i = start; i < end; i++) {
                        Car carToCheck = list.get(i);
                        if (Objects.equals(target, carToCheck)) {
                            count++;
                        }
                    }
                    return count;
                });
            }

            int total = 0;
            for (Future<Integer> future : executor.invokeAll(tasks)) {
                total += future.get();
            }
            return total;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw  new RuntimeException("Counting interrupted", e);
        } catch (ExecutionException e) {
            throw new RuntimeException("Failed to count", e);
        }
    }
}
