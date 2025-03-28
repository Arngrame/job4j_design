package ru.job4j.chapter01.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTreeTest {

    @Test
    void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenRootIsNotBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);

        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);

        assertThat(tree.isBinary()).isFalse();
    }

    @Test
    void whenOneOfChildIsNotBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);

        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 10);
        tree.add(2, 20);
        tree.add(2, 30);

        assertThat(tree.isBinary()).isFalse();
    }

    @Test
    void whenTreeIsBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);

        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 10);
        tree.add(2, 20);
        tree.add(3, 30);
        tree.add(3, 40);

        assertThat(tree.isBinary()).isTrue();
    }
}