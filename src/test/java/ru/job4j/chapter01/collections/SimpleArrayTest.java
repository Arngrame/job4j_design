package ru.job4j.chapter01.collections;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SimpleArrayTest {

    @Test
    public void addTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        assertThat(simpleArray.size()).isEqualTo(0);

        simpleArray.add(100500);
        assertThat(simpleArray.size()).isEqualTo(1);
        assertThat(simpleArray.get(0)).isEqualTo(100500);
    }

    @Test
    public void setAndGetTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);
        simpleArray.add(400);

        simpleArray.set(2, 330);

        assertThat(simpleArray.get(0)).isEqualTo(100);
        assertThat(simpleArray.get(1)).isEqualTo(200);
        assertThat(simpleArray.get(2)).isEqualTo(330);
        assertThat(simpleArray.get(3)).isEqualTo(400);
    }

    @Test
    public void removeInArrayStartTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);
        simpleArray.add(400);
        simpleArray.add(500);
        simpleArray.add(600);
        simpleArray.add(700);
        simpleArray.add(800);

        simpleArray.remove(0);

        assertThat(simpleArray.get(0)).isEqualTo(200);
        assertThat(simpleArray.get(1)).isEqualTo(300);
        assertThat(simpleArray.get(2)).isEqualTo(400);
        assertThat(simpleArray.get(3)).isEqualTo(500);
        assertThat(simpleArray.get(4)).isEqualTo(600);
        assertThat(simpleArray.get(5)).isEqualTo(700);
        assertThat(simpleArray.get(6)).isEqualTo(800);

        assertThat(simpleArray.size()).isEqualTo(7);
    }

    @Test
    public void removeInArrayMiddleTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);
        simpleArray.add(400);
        simpleArray.add(500);
        simpleArray.add(600);
        simpleArray.add(700);
        simpleArray.add(800);

        simpleArray.remove(5);

        assertThat(simpleArray.get(0)).isEqualTo(100);
        assertThat(simpleArray.get(1)).isEqualTo(200);
        assertThat(simpleArray.get(2)).isEqualTo(300);
        assertThat(simpleArray.get(3)).isEqualTo(400);
        assertThat(simpleArray.get(4)).isEqualTo(500);
        assertThat(simpleArray.get(5)).isEqualTo(700);
        assertThat(simpleArray.get(6)).isEqualTo(800);

        assertThat(simpleArray.size()).isEqualTo(7);
    }

    @Test()
    public void failureGetTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        assertThatThrownBy(() -> simpleArray.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test()
    public void failureSetTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        assertThatThrownBy(() -> simpleArray.set(0, 2)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test()
    public void failureRemoveMoreThan0Test() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);

        assertThatThrownBy(() -> simpleArray.remove(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test()
    public void failureRemoveMoreThanSizeTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);

        assertThatThrownBy(() -> simpleArray.remove(3)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void iteratorTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);
        simpleArray.add(400);

        Iterator<Integer> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext()).isEqualTo(true);
        assertThat(iterator.next()).isEqualTo(100);
        assertThat(iterator.hasNext()).isEqualTo(true);
        assertThat(iterator.next()).isEqualTo(200);
        assertThat(iterator.hasNext()).isEqualTo(true);
        assertThat(iterator.next()).isEqualTo(300);
        assertThat(iterator.hasNext()).isEqualTo(true);
        assertThat(iterator.next()).isEqualTo(400);
        assertThat(iterator.hasNext()).isEqualTo(false);
    }

}
