package day14;

import org.junit.jupiter.api.Test;

public class Day14Test {

    @Test
    void testExample() {
        Day14 day14 = new Day14("day14/example.txt");
        System.out.println("### part 1 ###");
        System.out.println(day14.applyMaskVersion1());
        System.out.println("### part 2 ###");
        System.out.println(day14.applyMaskVersion2());
    }

    @Test
    void testActual() {
        Day14 day14 = new Day14("day14/actual.txt");
        System.out.println("### part 1 ###");
        System.out.println(day14.applyMaskVersion1());
        System.out.println("### part 2 ###");
        System.out.println(day14.applyMaskVersion2());
    }

}