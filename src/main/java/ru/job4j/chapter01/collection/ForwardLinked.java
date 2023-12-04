package ru.job4j.chapter01.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    public int size() {
        return size;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            size++;
            return;
        }
        Node<T> previousHead = head;
        Node<T> newHead = new Node<>(value, previousHead);
        head = newHead;
        size++;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }

        Node<T> previousNode = null;
        Node<T> currentNode = head;
        Node<T> nextNode = head;
        while (currentNode != null) {
            nextNode = nextNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        head = previousNode;

        return true;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}