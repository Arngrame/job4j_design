package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int currentIndex;
    private int nextIndex;

    public NonNullIterator(Integer[] data) {
        this.data = data;
        currentIndex = -1;
        nextIndex = -1;
    }

    @Override
    public boolean hasNext() {
        for (int i = currentIndex + 1; i < data.length; i++) {
            if (data[i] != null) {
                nextIndex = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex = nextIndex;
        return data[currentIndex];
    }
}