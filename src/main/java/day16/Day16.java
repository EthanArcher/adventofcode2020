package day16;

import utils.FileReader;

import java.util.*;

public class Day16 {

    final List<Rule> rules = new ArrayList<>();
    final List<Integer> yourTicket;
    final List<List<Integer>> nearbyTickets = new ArrayList<>();

    public Day16(String filename) {
        List<String> lines = FileReader.readFileToString(filename);

        // create 3 lists of strings split by the empty line
        int firstBlank = lines.indexOf("");
        int secondBlank = lines.subList(firstBlank + 1, lines.size()).indexOf("") + firstBlank + 1;

        lines.subList(0, firstBlank).forEach(r -> {
            String[] m = r.split(":");
            String[] x = m[1].split(" or ");
            rules.add(new Rule(m[0].trim(), Range.parse(x[0]), Range.parse(x[1])));
        });

        // skip the "your ticket:" and "nearby tickets:" headers
        yourTicket = parseTicket(lines.get(firstBlank + 2));
        lines.subList(secondBlank + 2, lines.size())
                .forEach(ticket -> nearbyTickets.add(parseTicket(ticket)));
    }

    public int part1() {
        return nearbyTickets.stream()
                .flatMap(List::stream)
                .filter(value -> !isValid(value))
                .mapToInt(Integer::intValue)
                .sum();
    }

    public long part2() {
        Map<String, Integer> columns = assignColumns(findPossibilities(findValidTickets()));

        long answer = 1L;
        for (Map.Entry<String, Integer> column : columns.entrySet()) {
            if (column.getKey().startsWith("departure")) {
                answer *= yourTicket.get(column.getValue());
            }
        }
        return answer;
    }

    private List<Integer> parseTicket(String ticket) {
        return Arrays.stream(ticket.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private List<List<Integer>> findValidTickets() {
        return nearbyTickets.stream()
                .filter(ticket -> ticket.stream().allMatch(this::isValid))
                .toList();
    }

    private Map<String, Set<Integer>> findPossibilities(List<List<Integer>> validTickets) {
        Map<String, Set<Integer>> possibilities = new HashMap<>();

        for (Rule rule : rules) {
            // for each rule, find the columns its possible for
            // check that all the values in the column of valid tickets passes for the rule
            Set<Integer> columns = new HashSet<>();
            for (int c = 0; c < yourTicket.size(); c++) {
                int column = c;
                if (validTickets.stream().allMatch(ticket -> rule.matches(ticket.get(column)))) {
                    columns.add(column);
                }
            }
            possibilities.put(rule.name(), columns);
        }

        return possibilities;
    }

    private Map<String, Integer> assignColumns(Map<String, Set<Integer>> possibilities) {
        Map<String, Integer> results = new HashMap<>();

        while (!possibilities.isEmpty()) {
            Map.Entry<String, Set<Integer>> solved = possibilities.entrySet().stream()
                    .filter(entry -> entry.getValue().size() == 1)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No rule has a single possible column: " + possibilities));

            String name = solved.getKey();
            int column = solved.getValue().iterator().next();

            results.put(name, column);
            possibilities.remove(name);
            possibilities.values().forEach(columns -> columns.remove(column));
        }

        return results;
    }

    private boolean isValid(int value) {
        return rules.stream().anyMatch(rule -> rule.matches(value));
    }

}

record Rule(String name, Range lower, Range upper) {

    boolean matches(int value) {
        return lower.contains(value) || upper.contains(value);
    }
}

record Range(int min, int max) {

    static Range parse(String range) {
        String[] r = range.trim().split("-");
        return new Range(Integer.parseInt(r[0]), Integer.parseInt(r[1]));
    }

    boolean contains(int value) {
        return value >= min && value <= max;
    }
}
