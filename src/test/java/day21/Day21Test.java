package day21;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Test {

    @Test
    void testExamplePart1() {
        Day21 day21 = new Day21("day21/example.txt");
        assertEquals(5, day21.part1());
        assertEquals("mxmxvkd,sqjhc,fvjkl", day21.part2());
    }

    @Test
    void testActualPart1() {
        Day21 day21 = new Day21("day21/actual.txt");
        System.out.println(day21.part1());
        System.out.println(day21.part2());
    }

    @Test
    void intersectionOfLists() {
        Set<String> set1 = Set.of("mxmxvkd", "kfcds", "sqjhc", "nhms");
        Set<String> set2 = Set.of("trh", "fvjkl", "sbzzf", "mxmxvkd");

        assertEquals(Set.of("mxmxvkd"), Day21.intersection(set1, set2));
    }

}
