package day11;

import utils.AdventOfCodeUtils;
import utils.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Day11 {

    final String emptySeat = "L";
    final String filledSeat = "#";
    final char[][] grid;
    HashMap<Position, String> seats = new HashMap<>();
    final List<int[]> adjacents = AdventOfCodeUtils.getAdjacents();
    final int maxRow;
    final int maxColumn;
    HashMap<Position, String> originalSeats;

    public Day11(String filename) {
        grid = FileReader.readFileToCharGrid(filename);

        maxRow = grid.length;
        maxColumn = grid[0].length;

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (String.valueOf(grid[row][column]).equals(emptySeat)) {
                    seats.put(new Position(row, column), emptySeat);
                }
            }
        }

        originalSeats = new HashMap<>(seats);
    }

    public int countOccupiedSeatsAtLoop(boolean visibilityRules) {
        seats = new HashMap<>(originalSeats);
        int loops = 0;
        List<String> history = new ArrayList<>();
        while(!history.contains(seats.toString())) {
            history.add(seats.toString());
            if (visibilityRules) {
                applyVisibilityRules();
            } else {
                applyRules();
            }
            loops++;
        }

        System.out.println("Loops: " + loops);

        return countOccupiedSeats();

    }

    public void applyRules() {
        HashMap<Position, String> afterRulesApplied = new HashMap<>(seats);
        for (Position pos : seats.keySet()) {
            if (Objects.equals(seats.get(pos), emptySeat) && occupiedAdjacentSeats(pos) == 0) {
                afterRulesApplied.put(pos, filledSeat);
            }
            if (Objects.equals(seats.get(pos), filledSeat) && occupiedAdjacentSeats(pos) >= 4) {
                afterRulesApplied.put(pos, emptySeat);
            }
        }
        seats = afterRulesApplied;
    }

    public void applyVisibilityRules() {
        HashMap<Position, String> afterRulesApplied = new HashMap<>(seats);
        for (Position pos : seats.keySet()) {
            if (Objects.equals(seats.get(pos), emptySeat) && occupiedSeatsInView(pos) == 0) {
                afterRulesApplied.put(pos, filledSeat);
            }
            if (Objects.equals(seats.get(pos), filledSeat) && occupiedSeatsInView(pos) >= 5) {
                afterRulesApplied.put(pos, emptySeat);
            }
        }
        seats = afterRulesApplied;
    }

    public int occupiedAdjacentSeats(Position position) {
        int occupied = 0;
        for (int[] adj : adjacents) {
            if (Objects.equals(seats.get(new Position(position.row() + adj[0], position.column() + adj[1])), filledSeat)) {
                occupied++;
            }
        }
        return occupied;
    }

    public int occupiedSeatsInView(Position position) {
        int occupied = 0;

        for (int[] adj : adjacents) {
            int r = position.row() + adj[0];
            int c = position.column() + adj[1];

            while (r >= 0 && r < maxRow && c >= 0 && c < maxColumn) {
                if (Objects.equals(seats.get(new Position(r, c)), filledSeat)) {
                    occupied++;
                    break;
                }
                if (Objects.equals(seats.get(new Position(r, c)), emptySeat)) {
                    break;
                }

                r += adj[0];
                c += adj[1];
            }
        }

        return occupied;
    }

    public int countOccupiedSeats() {
        int occupiedCount = 0;
        for (String occ : seats.values()) {
            if (occ.equals(filledSeat)) {
                occupiedCount++;
            }
        }
        return occupiedCount;
    }
}

record Position(int row, int column){}