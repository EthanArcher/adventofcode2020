package day07;

import org.junit.jupiter.api.Test;

public class Day07Test {

    @Test
    void testExample() {
        Day07 day07 = new Day07("day07/example.txt");
        System.out.println(day07.bagsThatContain("shiny gold"));
        System.out.println(day07.totalBagsIn("shiny gold"));
    }

    @Test
    void testActual() {
        Day07 day07 = new Day07("day07/actual.txt");
        System.out.println(day07.bagsThatContain("shiny gold"));
        System.out.println(day07.totalBagsIn("shiny gold"));
    }

}