package day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test {

    @Test
    void testExample() {
        Day19 day19 = new Day19("day19/example.txt");
        assertEquals(2, day19.countPassingMessages(0));
    }

    @Test
    void testExample2() {
        Day19 day19 = new Day19("day19/example2.txt");
        assertEquals(3, day19.countPassingMessages(0));
        assertEquals(12, day19.countPassingMessagesWithLoops());

    }

    @Test
    void testActual() {
        Day19 day19 = new Day19("day19/actual.txt");
        System.out.println("Part 1");
        System.out.println(day19.countPassingMessages(0));
        System.out.println("Part 2");
        System.out.println(day19.countPassingMessagesWithLoops());
    }

}
