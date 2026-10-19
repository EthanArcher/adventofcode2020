package day13;

import org.junit.jupiter.api.Test;

public class Day13Test {

    @Test
    void testExample() {
        Day13 day13 = new Day13("day13/example.txt");
        System.out.println("Earliest bus: " + day13.getNextBus());
        System.out.println("Subsequent time: " + day13.findTimestamp());
    }

    @Test
    void testActual() {
        Day13 day13 = new Day13("day13/actual.txt");
        System.out.println("Earliest bus: " + day13.getNextBus());
        System.out.println("Subsequent time: " + day13.findTimestamp());
    }

}