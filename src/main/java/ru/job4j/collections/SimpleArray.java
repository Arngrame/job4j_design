package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;
    private int currentIndex;

    public SimpleArray() {
        data = new Object[DEFAULT_CAPACITY];
        currentIndex = 0;
        size = 0;
    }

    public void add(T model) {
        data[currentIndex++] = model;
        size++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        size--;
        System.arraycopy(data, index + 1, data, index, size - index);
        currentIndex = size - 1;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) data[index];
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>();
    }

    private class SimpleArrayIterator<T> implements Iterator<T> {

        int pointer = 0;

        SimpleArrayIterator() {

        }

        @Override
        public boolean hasNext() {
            return pointer < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) data[pointer++];
        }
    }

}
