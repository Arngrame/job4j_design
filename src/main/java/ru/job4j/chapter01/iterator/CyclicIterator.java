package ru.job4j.chapter01.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
        index = -1;
    }

    @Override
    public boolean hasNext() {
        return data != null && !data.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        index = index == data.size() - 1 ? 0 : index + 1;
        return data.get(index);
    }
}