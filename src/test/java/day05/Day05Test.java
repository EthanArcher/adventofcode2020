package day05;

import org.junit.jupiter.api.Test;

public class Day05Test {

    @Test
    void testExample() {
        Day05 day05 = new Day05("day05/example.txt");
        System.out.println(day05.findHighestSeatId());
        System.out.println(day05.findMySeatId());
    }

    @Test
    void testActual() {
        Day05 day05 = new Day05("day05/actual.txt");
        System.out.println(day05.findHighestSeatId());
        System.out.println(day05.findMySeatId());
    }

}
