package day03;

import utils.FileReader;

import java.util.List;

public class Day03 {

    private char[][] grid;

    public Day03(String filename) {
        grid = FileReader.readFileToCharGrid(filename);
    }

    public double transverseWithOptions(List<Transverse> transverseOptions) {

        return transverseOptions.stream()
                .mapToDouble(option -> transverseGrid(option.columnStep(), option.rowStep()))
                .reduce(1.0, (a, b) -> a * b);
    }

    public int transverseGrid(int columnStep, int rowStep) {
        int currentRow = 0;
        int currentCol = 0;
        int treeCount = 0;

        while(currentRow < (grid.length - 1)) {
            currentRow += rowStep;
            currentCol = (currentCol + columnStep) % grid[0].length;

            if (grid[currentRow][currentCol] == '#') {
                treeCount++;
            }
        }

        return treeCount;
    }

}

record Transverse(int columnStep, int rowStep) {
}
