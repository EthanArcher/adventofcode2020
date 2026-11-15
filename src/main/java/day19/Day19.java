package day19;

import utils.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day19 {

    final Map<Integer, List<List<String>>> rules;
    final List<String> messages;

    public Day19(String filename) {
        rules = new HashMap<>();
        messages = new ArrayList<>();

        boolean readingRules = true;

        for (String line : FileReader.readFileToString(filename)) {
            if (line.isBlank()) {
                readingRules = false;
                continue;
            }
            if (readingRules) {
                String[] rule = line.split(":");
                rules.put(Integer.parseInt(rule[0].trim()),
                        Arrays.stream(rule[1].split("\\|"))
                                .map(String::trim)
                                .map(s -> s.replace("\"", ""))
                                .map(s -> Arrays.stream(s.split(" ")).toList())
                                .toList()
                );
            } else {
                messages.add(line);
            }
        }
    }

    public List<String> expand(int ruleId) {
        List<String> result = new ArrayList<>();

        for (List<String> ruleContents : rules.get(ruleId)) {
            List<String> combos = new ArrayList<>();
            combos.add("");

            for (String x : ruleContents) {

                List<String> pieces = x.matches("\\d+")
                        ? expand(Integer.parseInt(x)) // if the value is an int, then expand the result of it
                        : List.of(x);

                List<String> next = new ArrayList<>();
                // for each of the combos so far
                for (String soFar : combos) {
                    // for each of the possible next values in the chain, add a new option for it
                    for (String piece : pieces) {
                        next.add(soFar + piece);
                    }
                }
                combos = next;
            }
            result.addAll(combos);
        }
        return result;
    }

    public int countPassingMessages(int ruleId) {
        Set<String> passing = new HashSet<>(expand(ruleId));

        int count = 0;
        for (String message : messages) {
            if (passing.contains(message)) {
                count++;
            }
        }
        return count;
    }

    /*
     * Part 2 replaces the rules with loops:
     *   0: 8 11
     *   8: 42 | 42 8           -> one or more 42s
     *   11: 42 31 | 42 11 31   -> n 42s followed by n 31s
     * So a valid message is some 42s followed by some 31s, with more 42s than 31s
     * and at least one 31. Only rules 42 and 31 are expanded, so the input file
     * should keep the original (non-looping) rules 8 and 11.
     */
    public int countPassingMessagesWithLoops() {
        Set<String> values42 = new HashSet<>(expand(42));
        Set<String> values31 = new HashSet<>(expand(31));
        int count = 0;

        for (String original : messages) {
            String message = original;   // local copy, messages list untouched
            int n42 = 0, n31 = 0;

            boolean stripped = true;
            while (stripped) {           // strip 42s off the front
                stripped = false;
                for (String prefix : values42) {
                    if (message.startsWith(prefix)) {
                        message = message.substring(prefix.length());
                        n42++;
                        stripped = true;
                        break;
                    }
                }
            }

            stripped = true;
            while (stripped) {           // remaining must be all 31s
                stripped = false;
                for (String postfix : values31) {
                    if (message.startsWith(postfix)) {
                        message = message.substring(postfix.length());
                        n31++;
                        stripped = true;
                        break;
                    }
                }
            }

            if (message.isEmpty() && n31 >= 1 && n42 > n31) count++;
        }
        return count;
    }
}
