package day09;

import org.junit.jupiter.api.Test;

public class Day09Test {

    @Test
    void testExample() {
        Day09 day09 = new Day09("day09/example.txt", 5);

        Long firstInvalid = day09.findFirstInvalid();
        System.out.println("NOT contained: " + firstInvalid);
        System.out.println(day09.findSequenceFor(firstInvalid));

    }

    @Test
    void testActual() {
        Day09 day09 = new Day09("day09/actual.txt", 25);

        Long firstInvalid = day09.findFirstInvalid();
        System.out.println("NOT contained: " + firstInvalid);
        System.out.println(day09.findSequenceFor(firstInvalid));

    }

}