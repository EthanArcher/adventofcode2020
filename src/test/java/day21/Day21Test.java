package day21;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Test {

    @Test
    void testExample() {
        Day21 day21 = new Day21("day21/example.txt");

    }

    @Test
    void testActual() {
        Day21 day21 = new Day21("day21/actual.txt");
    }

    @Test
    void intersectionOfLists() {
        Set<String> set1 = Set.of("mxmxvkd", "kfcds", "sqjhc", "nhms");
        Set<String> set2 = Set.of("trh", "fvjkl", "sbzzf", "mxmxvkd");

        assertEquals(Set.of("mxmxvkd"), Day21.intersection(set1, set2));
    }

}
