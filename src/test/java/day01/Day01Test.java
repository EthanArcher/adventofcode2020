package day01;

import org.junit.jupiter.api.Test;

public class Day01Test {

    @Test
    void testExample() {
        Day01 day01 = new Day01("day01/example.txt");
        System.out.println(day01.findSetOf2NumbersThatSumToTarget());
        System.out.println(day01.findSetOf3NumbersThatSumToTarget());
    }

    @Test
    void testActual() {
        Day01 day01 = new Day01("day01/actual.txt");
        System.out.println(day01.findSetOf2NumbersThatSumToTarget());
        System.out.println(day01.findSetOf3NumbersThatSumToTarget());
    }
}
