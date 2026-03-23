package com.aston.sorting.collection;

import java.util.AbstractList;
import java.util.Arrays;

public class CustomList<T> extends AbstractList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private Object[] elements;
    private int size;

    public CustomList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // --- Обязательные методы (без них AbstractList абстрактный) ---

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
        modCount++;
    }

    // --- Нужно для сортировки (в AbstractList бросают UnsupportedOperationException) ---

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T old = (T) elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T old = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        modCount++;
        return old;
    }

    // --- Приватные вспомогательные ---

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * GROWTH_FACTOR);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}