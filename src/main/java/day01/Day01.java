package day01;

import utils.AdventOfCodeUtils;
import utils.FileReader;

import java.util.List;

public class Day01 {

    private final List<Integer> numbers;
    private final int target = 2020;

    public Day01(String filename) {
        List<String> strings = FileReader.readFileToString(filename);
            numbers = AdventOfCodeUtils.toListOfIntegers(strings);

    }

    public String findSetOf2NumbersThatSumToTarget() {
        for (int i=0; i<numbers.size(); i++) {
            for (int j=i+1; j<numbers.size(); j++) {
                if (numbers.get(i) + numbers.get(j) == target) {
                    return String.format("Found: %d + %d = 2020, product = %d", numbers.get(i), numbers.get(j), numbers.get(i) * numbers.get(j));
                }
            }
        }
        return "Not found";
    }

    public String findSetOf3NumbersThatSumToTarget() {
        for (int i=0; i<numbers.size(); i++) {
            for (int j=i+1; j<numbers.size(); j++) {
                for (int k=j+1; k<numbers.size(); k++) {
                    if (numbers.get(i) + numbers.get(j) + numbers.get(k) == target) {
                        return String.format("Found: %d + %d + %d = 2020, product = %d", numbers.get(i), numbers.get(j), numbers.get(k), numbers.get(i) * numbers.get(j) * numbers.get(k));
                    }
                }
            }
        }
        return "Not found";
    }
}
