package ru.job4j.chapter01.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public int size() {
        return linked.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}
