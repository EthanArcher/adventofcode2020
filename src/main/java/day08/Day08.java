package day08;

import utils.FileReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 {

    final List<String> lines;

    public Day08(String filename) {
        lines = FileReader.readFileToString(filename);
    }

    public List<String> getLines() {
        return lines;
    }

    public Result operateUntilLoop(List<String> lines) {
        final Set<Integer> seen = new HashSet<>();
        int acc = 0;
        int line = 0;

        while (true) {
            if (seen.contains(line)) {
                return new Result(true, acc);
            }

            if (line == lines.size()) {
                return new Result(false, acc);
            }

            seen.add(line);
            String operation = lines.get(line);
            String[] split = operation.split(" ");
            int value = Integer.parseInt(split[1]);

            if (operation.contains("nop")){
                line++;
            } else if ((operation.contains("acc"))) {
                acc += value;
                line++;
            } else if ((operation.contains("jmp"))) {
                line += value;
            }
        }
    }

    public int findReplacement() {

        for (int i=0; i<lines.size(); i++) {
            List<String> linesCopy = new ArrayList<>(lines);
            Result r = null;
            if (lines.get(i).contains("jmp")) {
                linesCopy.set(i, lines.get(i).replaceFirst("jmp", "nop"));
                r = operateUntilLoop(linesCopy);
            } else if (lines.get(i).contains("nop")) {
                linesCopy.set(i, lines.get(i).replaceFirst("jmp", "nop"));
                r = operateUntilLoop(linesCopy);
            }
            if (r != null && !r.loop()) {
                return r.acc();
            }
        }

        return 0;
    }


}

record Result(boolean loop, int acc) {}