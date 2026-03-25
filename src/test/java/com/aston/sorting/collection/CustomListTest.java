package com.aston.sorting.collection;

import com.aston.sorting.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CustomListTest {

    CustomList<Car> list;

    // Перед каждым тестом обновляем коллекцию
    @BeforeEach
    void setUp() {
        list = new CustomList<>();
    }

    // Метод-обертка для более короткого и удобного создания объектов Car
    private Car createCar(int horsePower, String model, int year) {
        return new Car.Builder().
                horsePower(horsePower).
                model(model).
                year(year).
                build();
    }

    @Test
    void shouldReturnCorrectSizeAfterAdd() {
        // Проверка: добавить несколько элементов, проверить size()

        list.add(0, createCar(189, "Toyota", 2004));
        list.add(1, createCar(180, "Honda", 2006));
        list.add(2, createCar(135, "BMW", 2007));
        assertEquals(3, list.size(), "Результат не совпадает с ожидаемым");
    }

    @Test
    void shouldReturnElementByIndex() {
        // Проверка: добавить элемент, получить по индексу, проверить значение

        list.add(0, createCar(189, "Toyota", 2004));
        Car car1 = createCar(189, "Toyota", 2004);
        Car car2 = list.getFirst();
        assertEquals(car1, car2, "Результат не совпадает с ожидаемым");
    }

    @Test
    void shouldRemoveElementByIndex() {
        // Проверка: добавить несколько элементов, удалить один по индексу,
        //           проверить что size уменьшился на 1 и оставшиеся элементы сдвинулись корректно

        // 1. Добавление трех объектов
        list.add(0, createCar(189, "Toyota", 2004));
        list.add(1, createCar(180, "Honda", 2006));
        list.add(2, createCar(135, "BMW", 2007));

        // 2. Удаление объекта под индексом 1
        list.remove(1);

        // 3. Сохраняем новый размер коллекции после удаления объекта
        int newSize = list.size();
        int expectedSize = 2;

        // 4. Создаем экземпляр коллекции с ожидаемым наполнением объектами
        CustomList<Car> list2 = new CustomList<>();
        list2.add(0, createCar(189, "Toyota", 2004));
        list2.add(1, createCar(135, "BMW", 2007));

        // 5. Проверка
        assertEquals(expectedSize, newSize, "При удалении объекта размер коллекции изменился некорректно.");
        assertEquals(list2, list, "После удаления элемента из коллекции элементы в коллекции не сдвинулись корректно.");
    }

    @Test
    void shouldIterateOverAllElements() {
        // Проверка: добавить несколько элементов, пройтись через for-each,
        //           проверить что все элементы присутствуют

        // 1. Добавление трех объектов
        list.add(0, createCar(189, "Toyota", 2004));
        list.add(1, createCar(180, "Honda", 2006));
        list.add(2, createCar(135, "BMW", 2007));

        // 2. Прохождение по коллекции через forEach и копирование объектов в новую коллекцию
        CustomList<Car> list2 = new CustomList<>();
        list.forEach(list2::add);

        // 3. Проверка
        assertEquals(list2, list, "Не все элементы корректно добавлены в коллекцию");
    }

    @Test
    void shouldExpandCapacityWhenFull() {
        // Проверка: добавить больше элементов чем DEFAULT_CAPACITY (10),
        //           проверить что все элементы доступны и size корректен

        // 1. Заполняем через fori коллекцию 11 объектами
        for (int i = 0; i < 11; i++) {
            list.add(i, createCar(100 + i, "Car_" + i, 2000 + i));
        }

        // 2. С помощью метода addAll() копируем все элементы из созданной коллекции в новую коллекцию.
        // Тем самым убеждаемся, что все объекты созданы и корректно хранятся в оригинальной коллекции.
        CustomList<Car> list2 = new CustomList<>();
        list2.addAll(list);

        // 3. Проверка
        assertEquals(list2, list, "Не все элементы доступны");
        assertEquals(11, list.size(), "Размер коллекции некорректный");
    }

    @Test
    void shouldThrowWhenIndexOutOfBounds() {
        // Проверка: обратиться по индексу >= size(),
        //           проверить что выбрасывается IndexOutOfBoundsException

        // 1. Добавление трех объектов
        list.add(0, createCar(189, "Toyota", 2004));
        list.add(1, createCar(180, "Honda", 2006));
        list.add(2, createCar(135, "BMW", 2007));

        // 2. Проверка
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    void shouldThrowWhenGetOnEmptyList() {
        // Проверка: В пустой коллекции метод get(0) выбрасывается IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void shouldThrowWhenRemoveIndexOutOfBounds() {
        // Проверка: remove(5) на списке из 2 элементов → IndexOutOfBoundsException

        // 1. Добавление двух объектов
        list.add(0, createCar(189, "Toyota", 2004));
        list.add(1, createCar(180, "Honda", 2006));

        // 2. Проверка
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    @Test
    void shouldReplaceElementAtIndex() {
        // Проверка: добавить элемент, вызвать set(0, newValue),
        //           проверить что get(0) возвращает новое значение
        //           и что size не изменился

        // 1. Добавление одного объекта, сохранение его и размера коллекции
        list.add(0, createCar(189, "Toyota", 2004));
        Car oldCar = list.getFirst();
        int oldSize = list.size();

        // 2. Перезапись объекта с индексом 0 новым объектом, сохранение нового размера коллекции
        list.set(0, createCar(180, "Honda", 2006));
        Car newCar = list.getFirst();
        int newSize = list.size();

        // 3. Проверка
        assertEquals(oldSize, newSize, "После вызова метода set(0, newValue) размер изменился");
        assertNotEquals(oldCar, newCar, "После вызова метода set(0, newValue) объект не изменил значение на новое");
    }
}
