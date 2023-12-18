package ru.job4j.chapter01.iterator;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MatrixItTest {

    @Test
    public void testMatrix() {
        int[][] in = {
                {1, 2},
                {3, 4}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void testMatrixWithEmptyValues() {
        int[][] in = {
                {1, 2},
                {3, 4},
                {},
                {5, 8}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(5);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(8);
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void when4El() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenFirstEmptyThenHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.hasNext()).isFalse();
    }

    @Test()
    public void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);

        assertThatThrownBy(it::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void whenMultiHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);

        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenNoElements() {
        int[][] in = {{}, {}, {}};
        MatrixIt it = new MatrixIt(in);

        assertThat(it.hasNext()).isFalse();
    }
}