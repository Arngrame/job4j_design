package ru.job4j.chapter01.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        if (findBy(child).isPresent() || parentNode.isEmpty()) {
            return false;
        }

        return parentNode.get().children.add(new Node<>(child));
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> hasValuePredicate = node -> node.value.equals(value);
        return findByPredicate(hasValuePredicate);
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> nodeIsNotBinaryPredicate = node -> node.children.size() > 2;
        return findByPredicate(nodeIsNotBinaryPredicate).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }

}