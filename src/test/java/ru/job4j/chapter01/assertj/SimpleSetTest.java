package ru.job4j.chapter01.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void when4AddAndAddFirstThenFalse() {
        SimpleSet set = new SimpleSet();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("four");
        assertThat(set.add("first")).isFalse();
    }

}