package day12;

import org.junit.jupiter.api.Test;

public class Day12Test {

    @Test
    void testExample() {
        Day12 day12 = new Day12("day12/example.txt");
        System.out.println("Manhattan: " + day12.partA());
        System.out.println("Manhattan: " + day12.partB());
    }

    @Test
    void testActual() {
        Day12 day12 = new Day12("day12/actual.txt");
        System.out.println("Manhattan: " + day12.partA());
        System.out.println("Manhattan: " + day12.partB());
    }

}