package day14;

import utils.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day14 {

    final List<String> lines;
    String currentMask;

    public Day14(String filename) {
        lines = FileReader.readFileToString(filename);
    }

    public Long applyMaskVersion1() {
        HashMap<Integer, Long> dockingData = new HashMap<>();

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
                dockingData.put(address, result);

            }
        }

        Long sum = dockingData.values().stream().reduce(0L, Long::sum);
        return sum;
    }

    public Long applyMaskVersion2() {

        HashMap<Long, Long> dockingData = new HashMap<>();

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

                String binaryString = Long.toBinaryString(address);

                List<Long> addresses = applyMemoryAddressDecoder(binaryString);
                addresses.forEach(add -> dockingData.put(add, value));

            }
        }

        Long sum = dockingData.values().stream().reduce(0L, Long::sum);
        return sum;
    }

    public Long applyMask(String binaryString) {
        String[] mask = currentMask.split("");
        String[] binaryValue = binaryString.split("");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mask.length; i++) {
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
        return Long.valueOf(result.toString(), 2);
    }

    public List<Long> applyMemoryAddressDecoder(String address) {
        String[] add = address.split("");
        String[] mask = currentMask.split("");
        String[] address36 = new String[36];
        for (int i = 0; i < address36.length; i++) {
            if (i >= address36.length - add.length) {
                address36[i] = add[i - address36.length + add.length];
            } else {
                address36[i] = "0";
            }
        }

        List<StringBuilder> builders = new ArrayList<>();
        builders.add(new StringBuilder());
        for (int i = 0; i < mask.length; i++) {
            if (mask[i].equals("0")) {
                String s = address36[i];
                builders.forEach(sb -> sb.append(s));
            } else if (mask[i].equals("1")) {
                builders.forEach(sb -> sb.append("1"));
            } else if (mask[i].equals("X")) {
                List<StringBuilder> expanded = new ArrayList<>();
                for (StringBuilder sb : builders) {
                    expanded.add(new StringBuilder(sb).append('0'));
                    expanded.add(new StringBuilder(sb).append('1'));
                }
                builders.clear();
                builders.addAll(expanded);
            }
        }

        return builders.stream()
                .map(sb -> Long.valueOf(sb.toString(), 2))
                .collect(Collectors.toList());
    }

}