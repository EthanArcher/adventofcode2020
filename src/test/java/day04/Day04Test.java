package day04;

import org.junit.jupiter.api.Test;

public class Day04Test {

    @Test
    void testExample() {
        Day04 day04 = new Day04("day04/example.txt");
        System.out.println("Valid passports (part 1): " + day04.countValidPassports());
        System.out.println("Valid passports (part 2): " + day04.countStrictValidPassports());
    }

    @Test
    void testActual() {
        Day04 day04 = new Day04("day04/actual.txt");
        System.out.println("Valid passports (part 1): " + day04.countValidPassports());
        System.out.println("Valid passports (part 2): " + day04.countStrictValidPassports());
    }

}
