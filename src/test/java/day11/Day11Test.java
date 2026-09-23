package day11;

import org.junit.jupiter.api.Test;

public class Day11Test {

    @Test
    void testExample() {
        Day11 day11 = new Day11("day11/example.txt");
        System.out.println(day11.countOccupiedSeatsAtLoop(false));
        System.out.println(day11.countOccupiedSeatsAtLoop(true));
    }

    @Test
    void testActual() {
        Day11 day11 = new Day11("day11/actual.txt");
        System.out.println(day11.countOccupiedSeatsAtLoop(false));
        System.out.println(day11.countOccupiedSeatsAtLoop(true));

    }

}