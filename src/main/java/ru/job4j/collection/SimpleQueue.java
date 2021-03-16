package ru.job4j.collection;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.isEmpty()) {
            int inSize = in.size();
            while (inSize > 0) {
                out.push(in.pop());
                inSize--;
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        System.out.println(in.size());
    }
}
