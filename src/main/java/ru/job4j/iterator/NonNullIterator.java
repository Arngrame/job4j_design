package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
        index = -1;
    }

    @Override
    public boolean hasNext() {
        return getIndexOfFirstNotNull() != -1;
    }

    @Override
    public Integer next() {
        index = getIndexOfFirstNotNull();
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return data[index];
    }

    private int getIndexOfFirstNotNull() {
        for (int i = index + 1; i < data.length; i++) {
            if (data[i] != null) {
                return i;
            }
        }
        return -1;
    }
}