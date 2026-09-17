package day05;

import utils.FileReader;

import java.util.HashSet;
import java.util.List;

public class Day05 {

    final List<String> lines;
    int highestSeatId = 0;
    int lowestSeatId = Integer.MAX_VALUE;
    HashSet<Integer> seatIds = new HashSet<>();

    public Day05(String filename) {
        lines = FileReader.readFileToString(filename);
        for (String boardingPass : lines) {
            int seatId = findSeatId(boardingPass);
            seatIds.add(seatId);
            if (seatId > highestSeatId) {
                highestSeatId = seatId;
            }
            if (seatId < lowestSeatId) {
                lowestSeatId = seatId;
            }
        }
    }

    public int findHighestSeatId() {
        return highestSeatId;
    }

    public int findMySeatId() {
        for (int seatId = lowestSeatId; seatId < highestSeatId; seatId++) {
            if (!seatIds.contains(seatId)) {
                return seatId;
            }
        }
        return -1;
    }

    public int findSeatId(String boardingPass) {
        int row = getRow(boardingPass);
        int column = getColumn(boardingPass);
        return row * 8 + column;
    }

    public int getRow(String boardingPass) {
        return Integer.parseInt(
            boardingPass.substring(0, 7)
                .replace('F', '0')
                .replace('B', '1'),
            2
        );
    }

    public int getColumn(String boardingPass) {
        return Integer.parseInt(
            boardingPass.substring(7, 10)
                .replace('R', '1')
                .replace('L', '0'),
            2
        );
    }


}