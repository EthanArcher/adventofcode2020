package day17;

import utils.FileReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day17 {

    final List<String> lines;
    Set<Position> active = new HashSet<>();
    Set<Position> activeNext = new HashSet<>();

    public Day17(String filename) {
        lines = FileReader.readFileToString(filename);
        for (int x = 0; x < lines.size(); x++) {
            String line = lines.get(x);
            String[] val = line.split("");
            for (int y = 0; y < val.length; y++) {
                if (val[y].equals("#")) {
                    active.add(new Position(x, y, 0, 0));
                }
            }
        }
    }

    public int run(boolean useW) {
        int cols = lines.getFirst().length();

        for (int cycle = 1; cycle <= 6; cycle++) {
            int wMax = useW ? cycle : 0;

            for (int x = -cycle; x < cols + cycle; x++) {
                for (int y = -cycle; y < cols + cycle; y++) {
                    for (int z = -cycle; z < cols + cycle; z++) {
                        for (int w = -wMax; w <= wMax; w++) {
                            if (cycle(x, y, z, w, useW)) {
                                activeNext.add(new Position(x, y, z, w));
                            }
                        }
                    }
                }
            }
            active = activeNext;
            activeNext = new HashSet<>();
        }

        return active.size();
    }

    public boolean cycle(int x, int y, int z, int w, boolean useW) {
        /*
        If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
        If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
         */
        int lMax = useW ? 1 : 0;
        int activeCounter = 0;
        if (active.contains(new Position(x, y, z, w))) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    for (int k = -1; k < 2; k++) {
                        for (int l = -lMax; l <= lMax; l++) {
                            if (i == 0 && j == 0 && k == 0 && l == 0) {
                                continue;
                            }
                            if (active.contains(new Position(x + i, y + j, z + k, w + l))) {
                                activeCounter++;
                                if (activeCounter > 3) {
                                    // more than 3, break
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            if (activeCounter == 2 || activeCounter == 3) {
                return true;
            }
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int l = -lMax; l <= lMax; l++) {
                        if (i == 0 && j == 0 && k == 0 && l == 0) {
                            continue;
                        }

                        if (active.contains(new Position(x + i, y + j, z + k, w + l))) {
                            activeCounter++;
                            if (activeCounter > 3) {
                                // more than 3, break
                                return false;
                            }
                        }
                    }
                }
            }
        }
        if (activeCounter == 3) {
            return true;
        }
        return false;

    }
}

record Position(int x, int y, int z, int w) {
}
