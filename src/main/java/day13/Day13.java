package day13;

import utils.FileReader;

import java.math.BigInteger;
import java.util.*;

public class Day13 {

    final List<String> lines;
    final int earliestTime;
    HashMap<Integer, Integer> buses = new HashMap<>();
    int firstBus;

    public Day13(String filename) {
        lines = FileReader.readFileToString(filename);
        earliestTime = Integer.parseInt(lines.getFirst());
        String[] b = lines.getLast().split(",");
        firstBus = Integer.parseInt(b[0]);

        for (int i=0; i<b.length; i++) {
            if (!b[i].equals("x")) {
                buses.put(Integer.parseInt(b[i]), i);
            }
        }

        System.out.println(buses);

    }

    public long getNextBus() {
        int minutes = 0;
        while (true) {
            for (int bus : buses.keySet()) {
                if ((earliestTime + minutes) % bus == 0) {
                    return minutes * bus;
                }
            }
            minutes++;
        }

    }

    // Applying Chinese Remainder Theorem as per https://www.chineseremaindertheorem.com/calculator.php
    public long findTimestamp() {
        BigInteger M = buses.keySet().stream()
                .map(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
        BigInteger result = BigInteger.ZERO;

        for (int bus : buses.keySet()) {
            // Find the common modulus M
            BigInteger modulus = BigInteger.valueOf(bus);

            // We calculate the numbers M1 to M5
            BigInteger partialProduct = M.divide(modulus);

            // Calculate the modular multiplicative inverses M1-1 to M5-1
            long inverse = modularInverse(partialProduct.mod(modulus).longValueExact(), bus);
            BigInteger residue = BigInteger.valueOf(
                    Math.floorMod(-(long) buses.get(bus), (long) bus));

            // Calculate x with the equation we saw earlier
            result = result.add(residue.multiply(partialProduct).multiply(BigInteger.valueOf(inverse)));
        }

        return result.mod(M).longValueExact();
    }

    public static long modularInverse(long value, long modulus) {
        long[] result = extendedEuclid(value, modulus);
        if (result[0] != 1) {
            throw new IllegalArgumentException(value + " has no inverse modulo " + modulus);
        }
        return Math.floorMod(result[1], modulus);
    }

    private static long[] extendedEuclid(long a, long b) {
        if (b == 0) {
            return new long[]{a, 1, 0};
        }
        long[] result = extendedEuclid(b, a % b);
        long coefficient = result[1] - (a / b) * result[2];
        return new long[]{result[0], result[2], coefficient};
    }
}