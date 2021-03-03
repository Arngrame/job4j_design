package ru.job4j.collections;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void addTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();
        assertThat(simpleArray.size(), is(0));

        simpleArray.add(100500);
        assertThat(simpleArray.size(), is(1));
        assertThat(simpleArray.get(0), is(100500));
    }

    @Test
    public void setAndGetTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);
        simpleArray.add(400);

        simpleArray.set(2, 330);

        assertThat(simpleArray.get(0), is(100));
        assertThat(simpleArray.get(1), is(200));
        assertThat(simpleArray.get(2), is(330));
        assertThat(simpleArray.get(3), is(400));
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

        assertThat(simpleArray.get(0), is(200));
        assertThat(simpleArray.get(1), is(300));
        assertThat(simpleArray.get(2), is(400));
        assertThat(simpleArray.get(3), is(500));
        assertThat(simpleArray.get(4), is(600));
        assertThat(simpleArray.get(5), is(700));
        assertThat(simpleArray.get(6), is(800));

        assertThat(simpleArray.size(), is(7));
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

        assertThat(simpleArray.get(0), is(100));
        assertThat(simpleArray.get(1), is(200));
        assertThat(simpleArray.get(2), is(300));
        assertThat(simpleArray.get(3), is(400));
        assertThat(simpleArray.get(4), is(500));
        assertThat(simpleArray.get(5), is(700));
        assertThat(simpleArray.get(6), is(800));

        assertThat(simpleArray.size(), is(7));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failureGetTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failureSetTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.set(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failureRemoveMoreThan0Test() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);

        simpleArray.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void failureRemoveMoreThanSizeTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);

        simpleArray.remove(3);
    }

    @Test
    public void iteratorTest() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>();

        simpleArray.add(100);
        simpleArray.add(200);
        simpleArray.add(300);
        simpleArray.add(400);

        Iterator<Integer> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(100));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(200));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(300));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(400));
        assertThat(iterator.hasNext(), is(false));
    }

}
