package ru.job4j.chapter01.collection.set;

public interface Set<T> extends Iterable<T> {

    boolean add(T value);

    boolean contains(T value);
}
