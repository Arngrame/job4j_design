package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int modCount;
    private int size;
    private int capacity;
    private int index;

    private Object[] container;

    public SimpleArray() {
        container = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size == capacity) {
            grow();
        }
        container[index++] = model;
        modCount++;
        size++;
    }

    private void grow() {
        modCount++;
        capacity = capacity * 2;
        container = Arrays.copyOf(container, capacity);
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>();
    }

    private class SimpleArrayIterator<T> implements Iterator<T> {

        private final int expectedModCount;
        private int pointer = 0;

        public SimpleArrayIterator() {
            expectedModCount = modCount;
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
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return (T) container[pointer++];
        }
    }
}
