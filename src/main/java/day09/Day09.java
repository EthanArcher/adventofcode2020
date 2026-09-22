package day09;

import utils.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day09 {

    final List<String> lines;
    final List<Long> numbers = new ArrayList<>();
    final int preamble;
    HashMap<Integer, List<Long>> possibilities = new HashMap<>();

    public Day09(String filename, int preamble) {
        this.preamble = preamble;
        lines = FileReader.readFileToString(filename);
        lines.forEach(s -> numbers.add(Long.valueOf(s)));

        for (int i = 0; i < numbers.size(); i++) {
            List<Long> values = new ArrayList<>();

            for (int v = 1; v < preamble; v++) {
                // to the right
                if (i + v < numbers.size()) {
                    values.add(numbers.get(i) + numbers.get(i + v));
                }
            }
            possibilities.put(i, values);
        }

    }

    public Long findFirstInvalid(){

        List<Long> currentPossibleValues = new ArrayList<>();

        for (int pos=0; pos<preamble; pos++) {
            for (int v=1; v<preamble; v++) {
                // to the right
                if (pos+v < preamble) {
                    currentPossibleValues.add(numbers.get(pos) + numbers.get(pos+v));
                }
            }
        }

        for (int x=preamble; x< numbers.size(); x++) {
            if (!currentPossibleValues.contains(numbers.get(x))) {
                return numbers.get(x);
            }
            for (int range=1; range<preamble; range++) {
                currentPossibleValues.add(numbers.get(x) + numbers.get(x - range));
            }
            for (Long valueToRemove : possibilities.get(x - preamble)) {
                currentPossibleValues.remove(valueToRemove);
            }
        }
        return 0L;
    }

    public Long findSequenceFor(Long value) {
        Long total = 0L;

        int start = 0;
        int end = 0;
        int i=0;
        while(i< numbers.size()) {
            if (total > value) {
                total -= numbers.get(start);
                start++;
            } else {
                total += numbers.get(i);
                i++;
            }
            if (total.equals(value)) {
                System.out.println("Start: " + start + " End: " + i);
                end = i;
                break;
            }

        }

        Long min = Long.MAX_VALUE;
        Long max = 0L;
        for (int r=start; r<=end; r++) {
            Long num = numbers.get(r);
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        return min + max;

    }

}
