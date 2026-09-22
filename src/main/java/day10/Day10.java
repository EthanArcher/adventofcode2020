package day10;

import utils.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day10 {

    final List<String> lines;
    final List<Integer> joltages = new ArrayList<>();
    final List<Integer> ways = new ArrayList<>();


    public Day10(String filename) {
        lines = FileReader.readFileToString(filename);
        joltages.add(0);
        for (String l: lines) {
            joltages.add(Integer.parseInt(l));
        }

        Collections.sort(joltages);
        joltages.add(joltages.getLast() + 3);

        int oneCount = 0;
        int threeCount = 0;

        HashMap<Integer, List<Integer>> groups = new HashMap<>();
        List<Integer> group = new ArrayList<>();
        int groupId = 0;
        for (int i=0; i< joltages.size() - 1; i++) {
            int value = joltages.get(i);
            int diff = joltages.get(i + 1) - value;
            if (diff == 1) {
                oneCount++;
            }

            if (diff == 3) {
                threeCount++;
                group.add(value);
                groups.put(groupId, new ArrayList<>(group));
                ways.add(getWays(group.size()));
                group.clear();
                groupId++;
            } else {
                group.add(value);
            }
        }
        System.out.println("One: " + oneCount);
        System.out.println("Three: " + threeCount);
        System.out.println("mul: " + oneCount * threeCount);


    }

    public long countWays() {
        return ways.stream()
                .mapToLong(Integer::longValue)
                .reduce(1L, (a, b) -> a * b);
    }

    public int getWays(int size) {
        switch (size) {
            case 1: return 1;
            case 2: return 1;
            case 3: return 2;
            case 4: return 4;
            case 5: return 7;
            default: {
                System.out.println("error");
                return 0;
            }
        }
    }


}