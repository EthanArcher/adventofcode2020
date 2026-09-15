package utils;

import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCodeUtils {

    public static List<Integer> toListOfIntegers(List<String> listOfStrings) {
        return listOfStrings.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

}
