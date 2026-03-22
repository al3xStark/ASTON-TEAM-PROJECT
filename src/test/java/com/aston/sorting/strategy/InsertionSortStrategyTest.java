package com.aston.sorting.strategy;

import com.aston.sorting.comparator.CarComparators;
import com.aston.sorting.model.Car;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortStrategyTest {

    // Метод создает фиксированный список из 10 машин
    private List<Car> сarList() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car.Builder().horsePower(150).model("Toyota Corolla").year(2018).build());
        cars.add(new Car.Builder().horsePower(200).model("Ford Mustang").year(2020).build());
        cars.add(new Car.Builder().horsePower(120).model("Honda Civic").year(2017).build());
        cars.add(new Car.Builder().horsePower(180).model("BMW 3 Series").year(2019).build());
        cars.add(new Car.Builder().horsePower(160).model("Audi A4").year(2016).build());
        cars.add(new Car.Builder().horsePower(170).model("Volkswagen Golf").year(2018).build());
        cars.add(new Car.Builder().horsePower(220).model("Chevrolet Camaro").year(2021).build());
        cars.add(new Car.Builder().horsePower(130).model("Hyundai Elantra").year(2017).build());
        cars.add(new Car.Builder().horsePower(190).model("Mercedes C-Class").year(2019).build());

        return cars;
    }

    @Test
    public void shouldSortByHorsePower() {

        List<Car> cars = сarList();
        InsertionSortStrategy sorter = new InsertionSortStrategy();
        sorter.sort(cars, CarComparators.BY_HORSE_POWER);
        for (int i = 0; i < cars.size() - 1; i++) {
            int currentHp = cars.get(i).getHorsePower();
            int nextHp = cars.get(i + 1).getHorsePower();
            assertTrue(currentHp <= nextHp,
                    "Список не отсортирован по мощности: элемент " + i + " с мощностью " + currentHp +
                            " стоит перед элементом " + (i + 1) + " с мощностью " + nextHp);
        }
    }

    @Test
    public void shouldSortByModel() {
        List<Car> cars = сarList();
        InsertionSortStrategy sorter = new InsertionSortStrategy();
        sorter.sort(cars, CarComparators.BY_MODEL);
        for (int i = 0; i < cars.size() - 1; i++) {
            String currentModel = cars.get(i).getModel();
            String nextModel = cars.get(i + 1).getModel();
            assertTrue(currentModel.compareTo(nextModel) <= 0,
                    "Список не отсортирован по модели: элемент " + i + " с моделью " + currentModel +
                            " стоит перед элементом " + (i + 1) + " с моделью " + nextModel);
        }
    }

    @Test
    public void shouldSortByYear() {
        List<Car> cars = сarList();
        InsertionSortStrategy sorter = new InsertionSortStrategy();
        sorter.sort(cars, CarComparators.BY_YEAR);
        for (int i = 0; i < cars.size() - 1; i++) {
            int currentYear = cars.get(i).getYear();
            int nextYear = cars.get(i + 1).getYear();
            assertTrue(currentYear <= nextYear,
                    "Список не отсортирован по году выпуска: элемент " + i + " с годом " + currentYear +
                            " стоит перед элементом " + (i + 1) + " с годом " + nextYear);
        }
    }

    @Test
    public void shouldHandleEmptyList() {
        List<Car> emptyList = new ArrayList<>();
        InsertionSortStrategy sorter = new InsertionSortStrategy();

        assertDoesNotThrow(() -> sorter.sort(emptyList, CarComparators.BY_HORSE_POWER),
                "Сортировка пустого списка должна выполняться без исключений");
        assertTrue(emptyList.isEmpty(), "Пустой список должен остаться пустым после сортировки");
    }

    @Test
    public void shouldHandleSingleElement() {
        Car car = new Car.Builder()
                .horsePower(150)
                .model("Model Single")
                .year(2015)
                .build();
        List<Car> singleElementList = new ArrayList<>();
        singleElementList.add(car);
        InsertionSortStrategy sorter = new InsertionSortStrategy();
        assertDoesNotThrow(() -> sorter.sort(singleElementList, CarComparators.BY_HORSE_POWER),
                "Сортировка списка из одного элемента должна быть без исключений");
        assertEquals(1, singleElementList.size(), "Размер списка должен остаться равен 1");
        assertSame(car, singleElementList.get(0), "Единственный элемент должен остаться тем же объектом");
    }

    @Test
    public void shouldHandleListWithDuplicates() {
        Car car1 = new Car.Builder().horsePower(200).model("Model A").year(2010).build();
        Car car2 = new Car.Builder().horsePower(150).model("Model B").year(2011).build();
        Car car3 = new Car.Builder().horsePower(150).model("Model C").year(2012).build();
        Car car4 = new Car.Builder().horsePower(180).model("Model D").year(2013).build();
        Car car5 = new Car.Builder().horsePower(150).model("Model E").year(2014).build();

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);

        InsertionSortStrategy sorter = new InsertionSortStrategy();
        sorter.sort(cars, CarComparators.BY_HORSE_POWER);
        List<Car> expectedOrder = List.of(car2, car3, car5, car4, car1);
        assertEquals(expectedOrder.size(), cars.size(), "Размер списка после сортировки не совпадает");
        for (int i = 0; i < expectedOrder.size(); i++) {
            assertSame(expectedOrder.get(i), cars.get(i),
                    "Элемент с индексом " + i + " не на своем месте. Ожидалось: " +
                            expectedOrder.get(i) + ", вместо: " + cars.get(i));
        }
    }
}