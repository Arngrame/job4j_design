package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
            return;
        }
        Node<T> previousHead = head;
        Node<T> newHead = new Node<>(value, previousHead);
        head = newHead;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }

        Node<T> previousNode = null;
        Node<T> currentNode = head;
        Node<T> nextNode = head;
        while (currentNode != null) {
            nextNode = nextNode.next; // get next of next
            currentNode.next = previousNode; // set previous node as next of current node
            previousNode = currentNode; // previous node set current node
            currentNode = nextNode; // current node points to next node
        }
        head = previousNode; // last "previous" node becomes head

        return true;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
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