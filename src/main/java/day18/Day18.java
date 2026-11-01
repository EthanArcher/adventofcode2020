package day18;

import utils.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Day18 {

    final List<String> lines;

    public Day18(String filename) {
        lines = FileReader.readFileToString(filename);
    }

    public long getTotal(boolean addition) {
        long total = 0;
        for (String line : lines) {
            total += evaluate(line, addition);
        }
        return total;
    }

    public long evaluate(String equation, boolean addition) {
        return evaluate(new ArrayList<>(List.of(
                equation.replace(" ", "")
                        .split(""))), addition);
    }

    public long evaluate(List<String> equation, boolean addition) {
        while (equation.contains("(")) {
            int depth = 0;
            int from = 0;
            for (int i = 0; i < equation.size(); i++) {
                if (equation.get(i).equals("(")) {
                    depth++;
                    if (depth == 1) {
                        from = i;
                    }
                }
                if (equation.get(i).equals(")")) {
                    depth--;
                    if (depth == 0) {
                        long result = evaluate(new ArrayList<>(equation.subList(from + 1, i)), addition);
                        equation.subList(from, i).clear();
                        equation.set(from, String.valueOf(result));
                        break;
                    }
                }
            }
        }

        if (addition) {
            while (equation.contains("+")) {
                int index = equation.indexOf("+");
                long sum = Long.parseLong(equation.get(index - 1)) + Long.parseLong(equation.get(index + 1));
                equation.remove(index);
                equation.remove(index);
                equation.set(index - 1, String.valueOf(sum));
            }
        }

        long total = Long.parseLong(equation.getFirst());

        for (int i = 1; i < equation.size(); i++) {
            if (equation.get(i).equals("+")) {
                i++;
                total += Long.parseLong(equation.get(i));
            }
            if (equation.get(i).equals("*")) {
                i++;
                total *= Long.parseLong(equation.get(i));
            }
        }
        return total;
    }
}
