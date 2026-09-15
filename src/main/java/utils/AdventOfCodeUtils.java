package utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCodeUtils {

    public static List<Integer> toListOfIntegers(List<String> listOfStrings) throws IOException {
        return listOfStrings.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

}
