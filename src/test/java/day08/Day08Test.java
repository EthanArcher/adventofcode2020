package day08;

import org.junit.jupiter.api.Test;

public class Day08Test {

    @Test
    void testExample() {
        Day08 day08 = new Day08("day08/example.txt");
        System.out.println(day08.operateUntilLoop(day08.getLines()).acc());
        System.out.println(day08.findReplacement());
    }

    @Test
    void testActual() {
        Day08 day08 = new Day08("day08/actual.txt");
        System.out.println(day08.operateUntilLoop(day08.getLines()).acc());
        System.out.println(day08.findReplacement());
    }

}