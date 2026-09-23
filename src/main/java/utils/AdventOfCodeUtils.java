package utils;

import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCodeUtils {

    public static List<Integer> toListOfIntegers(List<String> listOfStrings) {
        return listOfStrings.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<int[]> getAdjacents() {
        return List.of(
                new int[]{-1, -1},
                new int[]{-1, 0},
                new int[]{-1, 1},
                new int[]{0, -1},
                new int[]{0, 1},
                new int[]{1, -1},
                new int[]{1, 0},
                new int[]{1, 1}
        );
    }

}
