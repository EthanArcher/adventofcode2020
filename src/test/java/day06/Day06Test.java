package day06;

import org.junit.jupiter.api.Test;

public class Day06Test {

    @Test
    void testExample() {
        Day06 day06 = new Day06("day06/example.txt");
        System.out.println(day06.sumUniqueAnswers());
        System.out.println(day06.sumCommonAnswers());
    }

    @Test
    void testActual() {
        Day06 day06 = new Day06("day06/actual.txt");
        System.out.println(day06.sumUniqueAnswers());
        System.out.println(day06.sumCommonAnswers());
    }

}