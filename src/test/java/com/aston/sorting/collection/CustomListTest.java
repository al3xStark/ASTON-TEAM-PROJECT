package com.aston.sorting.collection;

import org.junit.jupiter.api.Test;

class CustomListTest {

    @Test
    void shouldReturnCorrectSizeAfterAdd() {
        // TODO: добавить несколько элементов, проверить size()
    }

    @Test
    void shouldReturnElementByIndex() {
        // TODO: добавить элемент, получить по индексу, проверить значение
    }

    @Test
    void shouldRemoveElementByIndex() {
        // TODO: добавить несколько элементов, удалить один по индексу,
        //       проверить что size уменьшился на 1 и оставшиеся элементы сдвинулись корректно
    }

    @Test
    void shouldIterateOverAllElements() {
        // TODO: добавить несколько элементов, пройтись через for-each,
        //       проверить что все элементы присутствуют
    }

    @Test
    void shouldExpandCapacityWhenFull() {
        // TODO: добавить больше элементов чем DEFAULT_CAPACITY (10),
        //       проверить что все элементы доступны и size корректен
    }

    @Test
    void shouldThrowWhenIndexOutOfBounds() {
        // TODO: обратиться по индексу >= size(),
        //       проверить что выбрасывается IndexOutOfBoundsException
    }

    @Test
    void shouldThrowWhenGetOnEmptyList() {
        // TODO: создать пустой CustomList, вызвать get(0),
        //       проверить что выбрасывается IndexOutOfBoundsException
    }

    @Test
    void shouldThrowWhenRemoveIndexOutOfBounds() {
        // TODO: remove(5) на списке из 2 элементов → IndexOutOfBoundsException
    }
}
