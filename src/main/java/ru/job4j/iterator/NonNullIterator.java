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
        for (int i = index + 1; i < data.length; i++) {
            if (data[i] != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            for (int i = index + 1; i < data.length; i++) {
                if (data[i] != null) {
                    index = i;
                    return data[index];
                }
            }
        }
        throw new NoSuchElementException();
    }
}