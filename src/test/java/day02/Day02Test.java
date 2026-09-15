package day02;

import org.junit.jupiter.api.Test;

public class Day02Test {

    @Test
    void testExample() {
        Day02 day02 = new Day02("day02/example.txt");
        day02.testPasswords();
        day02.testUpdatedPasswords();
    }

    @Test
    void testActual() {
        Day02 day02 = new Day02("day02/actual.txt");
        day02.testPasswords();
        day02.testUpdatedPasswords();
    }
}
