package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        // check row index in matrix borders
        if (row < data.length) {
            // check column index less than current row length
            if (column < data[row].length) {
                return true;
            }

            // in other cases row should be increased and column should set 0 due to new row started
            column = 0;
            row++;

            // increase row index until reach row border
            while (row != data.length) {
                if (data[row].length == 0) {
                    row++;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return data[row][column++];
    }
}
