package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;
    private boolean goNextRow = false;

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
            } else if (column == data[row].length && data[row].length != 0) { // check breakline is needed if column index equals current row length
                // check if next line exists
                if (row + 1 == data.length) {
                    return false;
                }
                row++;
                column = 0;
                return true;
            } else if (data[row].length == 0) { //current row length = 0
                while (data[row].length == 0) { // check next rows
                    row++;
                    if (row == data.length) { // reach max row?
                        return false;
                    }
                }

                if (data[row].length != 0) {
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

        if (data[row].length == 0) {
            while (data[row].length == 0) {
                row++;
            }
        }

        return data[row][column++];
    }
}
