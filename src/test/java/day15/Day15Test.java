package day15;

import org.junit.jupiter.api.Test;

public class Day15Test {

    @Test
    void testExample() {
        Day15 day15 = new Day15("day15/example.txt");
        day15.findTheNumber(2020);
        day15.findTheNumber(30000000);
    }

    @Test
    void testActual() {
        Day15 day15 = new Day15("day15/actual.txt");
        day15.findTheNumber(2020);
        day15.findTheNumber(30000000);
    }

}