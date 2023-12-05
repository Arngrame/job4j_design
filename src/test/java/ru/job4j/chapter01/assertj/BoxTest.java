package ru.job4j.chapter01.assertj;

import org.junit.jupiter.api.Test;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(-1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isVertexNegativeOneBecauseOfDefaultType() {
        Box box = new Box(5, 10);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(-1);
    }

    @Test
    void isVertexNegativeOneBecauseOfEdge() {
        Box box = new Box(5, 0);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(-1);
    }

    @Test
    void isVertexNegativeOneTrue() {
        Box box = new Box(0, 10);
        boolean exist = box.isExist();
        assertThat(exist).isEqualTo(true);
    }

    @Test
    void isVertexNegativeOneFalse() {
        Box box = new Box(5, 0);
        boolean exist = box.isExist();
        assertThat(exist).isEqualTo(false);
    }

    @Test
    void isAreaEqualZero() {
        Box box = new Box(5, 0);
        double area = box.getArea();
        assertThat(area).isEqualTo(0.0);
    }

    @Test
    void isAreaEqualNotZero() {
        Box box = new Box(0, 1);
        double area = box.getArea();
        assertThat(area)
                .isEqualTo(12.56d, withPrecision(0.007d))
                .isCloseTo(12.56d, withPrecision(0.01d))
                .isCloseTo(12.56d, Percentage.withPercentage(1.0d))
                .isGreaterThan(12.55d)
                .isLessThan(12.57d);
    }
}