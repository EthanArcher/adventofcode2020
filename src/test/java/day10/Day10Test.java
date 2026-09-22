package day10;

import org.junit.jupiter.api.Test;

public class Day10Test {

    @Test
    void testExample() {
        Day10 day10 = new Day10("day10/example.txt");
        System.out.println(day10.countWays());
    }

    @Test
    void testActual() {
        Day10 day10 = new Day10("day10/actual.txt");
        System.out.println(day10.countWays());

    }

}