package ru.job4j.chapter01.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.NoSuchElementException;

public class SimpleQueueTest {

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.poll();
        queue.push(2);
        int rsl = queue.poll();
        assertThat(rsl, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.poll();
    }

    @Test
    public void whenPushPushPollAndPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.poll();
        queue.push(3);
        assertThat(queue.poll(), is(2));
    }

    @Test
    public void when2PushesPollAndPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        assertThat(queue.poll(), is(1));
        queue.push(3);
        assertThat(queue.poll(), is(2));
    }

    @Test
    public void multiplePush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        queue.push(6);
        queue.push(7);
        queue.push(8);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        queue.push(9);
        assertThat(queue.poll(), is(3));
    }
}