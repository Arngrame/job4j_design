package ru.job4j.chapter01.iterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EvenItTest {

    private Iterator<Integer> it;

    public void setUp() {
        it = new EvenIt(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test()
    public void shouldReturnEvenNumbersSequentially() {
        setUp();

        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(6);
        assertThat(it.hasNext()).isFalse();

        assertThatThrownBy(it::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        setUp();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.next()).isEqualTo(6);
    }

    @Test
    public void shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenIt(new int[]{1});
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenIt(new int[]{2, 4, 6, 8});

        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(6);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(8);
    }
}