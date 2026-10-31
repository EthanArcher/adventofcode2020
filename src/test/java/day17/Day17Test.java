package day17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17Test {

    @Test
    void testExample() {
        assertEquals(112, new Day17("day17/example.txt").run(false));
        assertEquals(848, new Day17("day17/example.txt").run(true));
    }

    @Test
    void testActual() {
        System.out.println(new Day17("day17/actual.txt").run(false));
        System.out.println(new Day17("day17/actual.txt").run(true));
    }

}
