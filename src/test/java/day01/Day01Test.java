package day01;

import org.junit.jupiter.api.Test;

public class Day01Test {

    @Test
    void day01test() {
        Day01 day01 = new Day01(2020);
        System.out.println(day01.findSetOf2NumbersThatSumToTarget());
        System.out.println(day01.findSetOf3NumbersThatSumToTarget());
    }
}
