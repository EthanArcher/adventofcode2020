package day14;

import utils.FileReader;

import java.util.HashMap;
import java.util.List;

public class Day14 {

    final List<String> lines;
    String currentMask;
    HashMap<Integer, Long> dockingData = new HashMap<>();

    public Day14(String filename) {
        lines = FileReader.readFileToString(filename);

        for (String line : lines) {
            String[] split = line.split(" = ");
            if (split[0].contains("mask")) {
                currentMask = split[1];
            } else {
                // apply the mask and write the value to the dockingData

                // read the memory address
                Integer address = Integer.valueOf(split[0].substring(4, split[0].length() - 1));

                // read the value
                Long value = Long.valueOf(split[1]);
                String binaryString = Long.toBinaryString(value);

                Long result = applyMask(binaryString);
                System.out.println("res: " + result);

                dockingData.put(address, result);

            }
        }

        Long sum = dockingData.values().stream().reduce(0L, Long::sum);
        System.out.println(sum);
    }

    public Long applyMask(String binaryString) {
        String[] mask = currentMask.split("");
        String[] binaryValue = binaryString.split("");
        StringBuilder result = new StringBuilder();
        for (int i=0; i<mask.length; i++) {
            String v = mask[i];
            if (!v.equals("X")) {
                result.append(v);
            } else {
                if (mask.length - i <= binaryValue.length) {
                    result.append(binaryValue[i - mask.length + binaryValue.length]);
                } else {
                    result.append(0);
                }
            }
        }
        System.out.println(result);
        return Long.valueOf(result.toString(), 2);
    }


}