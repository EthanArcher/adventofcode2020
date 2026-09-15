package day03;

import org.junit.jupiter.api.Test;

import java.util.List;

public class Day03Test {

    List<Transverse> transverseOptions = List.of(
            new Transverse(1, 1),
            new Transverse(3, 1),
            new Transverse(5, 1),
            new Transverse(7, 1),
            new Transverse(1, 2)
    );

    @Test
    void testExample() {
        Day03 day03 = new Day03("day03/example.txt");
        System.out.println(day03.transverseGrid(3, 1));
        System.out.printf("%f", day03.transverseWithOptions(transverseOptions));
    }

    @Test
    void testActual() {
        Day03 day03 = new Day03("day03/actual.txt");
        System.out.println(day03.transverseGrid(3, 1));
        System.out.printf("%f", day03.transverseWithOptions(transverseOptions));
    }
}
