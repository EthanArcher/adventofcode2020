package day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day18Test {

    @Test
    void testExample() {
        Day18 day18 = new Day18("day18/example.txt");

        assertEquals(26, day18.evaluate("2 * 3 + (4 * 5)", false));
        assertEquals(437, day18.evaluate("5 + (8 * 3 + 9 + 3 * 4 * 3)", false));
        assertEquals(12240, day18.evaluate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", false));
        assertEquals(13632, day18.evaluate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", false));

        assertEquals(46, day18.evaluate("2 * 3 + (4 * 5)", true));
        assertEquals(1445, day18.evaluate("5 + (8 * 3 + 9 + 3 * 4 * 3)", true));
        assertEquals(669060, day18.evaluate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", true));
        assertEquals(23340, day18.evaluate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", true));

        assertEquals(51, day18.getTotal(false));
        assertEquals(51, day18.getTotal(true));
    }

    @Test
    void testActual() {
        Day18 day18 = new Day18("day18/actual.txt");
        System.out.println(day18.getTotal(false));
        System.out.println(day18.getTotal(true));
    }

}
