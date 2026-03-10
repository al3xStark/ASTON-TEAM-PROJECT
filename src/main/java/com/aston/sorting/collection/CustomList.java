package com.aston.sorting.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public CustomList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(T element) {
        // TODO: добавить элемент, при необходимости расширить массив через ensureCapacity()
        return false;
    }

    @Override
    public void add(int index, T element) {
        // TODO: вставить элемент по индексу, сдвинуть остальные вправо
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        // TODO: проверить границы через checkIndex() и вернуть элемент
        return null;
    }

    @Override
    public T set(int index, T element) {
        // TODO: проверить границы, заменить элемент по индексу, вернуть старый
        return null;
    }

    @Override
    public T remove(int index) {
        // TODO: удалить элемент по индексу, сдвинуть остальные влево
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO: найти через indexOf() и удалить первое вхождение
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO: вернуть true если size == 0
        return false;
    }

    @Override
    public boolean contains(Object o) {
        // TODO: делегировать в indexOf()
        return false;
    }

    @Override
    public int indexOf(Object o) {
        // TODO: линейный поиск через Objects.equals()
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO: линейный поиск с конца через Objects.equals()
        return -1;
    }

    @Override
    public void clear() {
        // TODO: обнулить элементы через Arrays.fill() и сбросить size
    }

    @Override
    public Iterator<T> iterator() {
        // TODO: реализовать анонимный Iterator с полем cursor, hasNext() и next()
        return null;
    }

    @Override
    public Object[] toArray() {
        // TODO: вернуть копию внутреннего массива через Arrays.copyOf()
        return null;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        // TODO: скопировать элементы в переданный массив, при необходимости расширить его
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // TODO: добавить все элементы коллекции через цикл и add()
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        // TODO: вставить все элементы коллекции начиная с index
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO: вернуть true если все элементы c присутствуют через contains()
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO: удалить все элементы которые есть в c, идти с конца
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO: удалить все элементы которых нет в c, идти с конца
        return false;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList не поддерживается");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("listIterator не поддерживается");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("listIterator не поддерживается");
    }
}