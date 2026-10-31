package day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {

    @Test
    void testExample() {
        Day16 day16 = new Day16("day16/example.txt");
        assertEquals(71, day16.part1());
    }

    @Test
    void testActual() {
        Day16 day16 = new Day16("day16/actual.txt");
        assertEquals(27911, day16.part1());
        assertEquals(737176602479L, day16.part2());
    }

}
