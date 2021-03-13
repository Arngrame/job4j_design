package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T value = in.pop();
        out.pushToTail(value);
        return value;
    }

    public void push(T value) {
        in.pushToTail(value);
    }
}
