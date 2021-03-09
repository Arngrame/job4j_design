package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DynamicContainer<T> implements Iterable<T> {

    private int size;
    private int modCount;
    private Node<T> head;
    private Node<T> tail;

    public DynamicContainer() {
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> found = findNodeBy(index);
        return (T) found.value;
    }

    private Node<T> findNodeBy(int index) {
        Node<T> current = head;
        if (index == 0) {
            return current;
        }

        for (int i = 0; i < index; ++i) {
            current = current.next;
        }

        return current;
    }

    public void add(T model) {
        Node<T> oldTailNode = tail;
        Node<T> newTailNode = new Node<>(oldTailNode, (T) model, null);
        tail = newTailNode;

        if (oldTailNode == null) {
            head = newTailNode;
        } else {
            oldTailNode.next = newTailNode;
        }
        modCount++;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new DynamicArrayIterator<>();
    }

    private class DynamicArrayIterator<T> implements Iterator<T> {

        private final int expectedModCount;
        private Node currentNode;
        private Node returnedNode;
        private int pointer = 0;

        public DynamicArrayIterator() {
            this.currentNode = head;
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return pointer < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            returnedNode = currentNode;
            currentNode = currentNode.next;
            pointer++;
            return (T) returnedNode.value;
        }
    }

    private class Node<T> {

        Node<T> previous;
        T value;
        Node<T> next;

        public Node() {
        }

        public Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }
}
