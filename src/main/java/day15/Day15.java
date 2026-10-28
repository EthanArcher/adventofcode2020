package day15;

import utils.FileReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day15 {

    final List<String> lines;
    final HashMap<Integer, Integer> said = new HashMap<>();
    int lastSaid;
    int turn = 1;
    int next;

    public Day15(String filename) {
        lines = FileReader.readFileToString(filename);
        String line = lines.get(0);
        String[] values = line.split(",");
        Arrays.stream(values)
                .map(Integer::parseInt)
                .forEach(i -> {
                    said.put(i, turn);
                    lastSaid = i;
                    turn++;
                    next = 0;
                });
    }

    public void findTheNumber(int turns) {
        for (int i = turn; i <= turns; i++) {
            sayNextNumber();
            turn++;
        }
        System.out.println(lastSaid);
    }

    public void sayNextNumber() {
        int thisTurn = next;
        if (said.containsKey(next)) {
            next = turn - said.get(thisTurn);
        } else {
            next = 0;
        }

        said.put(thisTurn, turn);

        lastSaid = thisTurn;
    }


}