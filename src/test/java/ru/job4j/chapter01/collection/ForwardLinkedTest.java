package ru.job4j.chapter01.collection;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Iterator;

public class ForwardLinkedTest {

    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenAddAndRevertThenIterAndAdd() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        linked.add(3);
        linked.add(4);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        ForwardLinked<Integer> emptyList = new ForwardLinked<>();
        assertFalse(emptyList.revert());
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        ForwardLinked<Integer> singleList = new ForwardLinked<>();
        singleList.add(1);
        assertFalse(singleList.revert());
    }

}