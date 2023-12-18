package ru.job4j.chapter01.iterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FlatMapTest {

    @Test
    public void rightOrderTest() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next()).isEqualTo(1);
        assertThat(flat.next()).isEqualTo(2);
        assertThat(flat.next()).isEqualTo(3);
        assertThat(flat.next()).isEqualTo(4);
        assertThat(flat.next()).isEqualTo(5);
        assertThat(flat.next()).isEqualTo(6);
        assertThat(flat.next()).isEqualTo(7);
        assertThat(flat.next()).isEqualTo(8);
        assertThat(flat.next()).isEqualTo(9);
    }

    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next()).isEqualTo(1);
        assertThat(flat.next()).isEqualTo(2);
        assertThat(flat.next()).isEqualTo(3);
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next()).isEqualTo(1);
        assertThat(flat.next()).isEqualTo(2);
        assertThat(flat.next()).isEqualTo(3);
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.hasNext()).isTrue();
        assertThat(flat.hasNext()).isTrue();
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertThat(flat.next()).isEqualTo(1);
        assertThat(flat.hasNext()).isFalse();
    }

    @Test()
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(
                List.of().iterator()
        ).iterator();
        FlatMap<Object> flat = new FlatMap<>(data);

        assertThatThrownBy(flat::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> data = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of(1).iterator()
        ).iterator();

        FlatMap<Object> flat = new FlatMap(data);

        assertThat(flat.hasNext()).isTrue();
        assertThat(1).isEqualTo(flat.next());
    }
}