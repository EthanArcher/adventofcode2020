package day20;

import utils.FileReader;

import java.util.*;
import java.util.function.Predicate;

public class Day20 {

    final List<String> lines;
    final HashMap<Integer, Tile> tiles = new HashMap<>();
    final HashMap<Integer, Tile> remainingTiles = new HashMap<>();
    final HashMap<String, Integer> edges = new HashMap<>();

    public Day20(String filename) {
        lines = FileReader.readFileToString(filename);

        int tileNumber = 0;
        List<String> image = new ArrayList<>();
        for (String line : lines) {
            if (line.contains("Tile")) {
                tileNumber = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.length() - 1));
            } else if (line.isBlank()) {
                tiles.put(tileNumber, new Tile(tileNumber, image));
                image = new ArrayList<>();
            } else {
                image.add(line);
            }
        }
        if (!image.isEmpty()) {
            tiles.put(tileNumber, new Tile(tileNumber, image));
        }
        remainingTiles.putAll(tiles);

        for (Tile tile : tiles.values()) {
            for (String side : tile.sides) {
                addToEdgesMap(side);
            }
        }
    }

    public List<Tile> getCornerTiles() {

        List<Tile> cornerTiles = new ArrayList<>();

        for (Tile tile : tiles.values()) {
            int count = 0;
            for (String side : tile.sides) {
                count += edges.get(side);
            }
            tile.setEdgeMatches(count);
            if (count == 6) {
                cornerTiles.add(tile);
            }
        }
        return cornerTiles;
    }

    public long productOfTiles(List<Tile> tiles) {
        long corners = 1;
        for (Tile tile : tiles) {
            corners *= tile.tileNumber;
        }
        return corners;
    }

    public void addToEdgesMap(String edge) {
        if (edges.containsKey(edge)) {
            edges.put(edge, edges.get(edge) + 1);
        } else {
            edges.put(edge, 1);
        }
    }

    public boolean isBorder(String side) {
        return edges.get(Tile.canonicalSide(side)) == 1;
    }

    public Tile assembleImage(int tileId) {
        int grid = (int) Math.sqrt(tiles.size());
        Tile[][] view = new Tile[grid][grid];

        Tile topLeft = tiles.get(tileId);
        topLeft.orientUntil(t -> isBorder(t.top) && isBorder(t.left));

        Tile lastTile = topLeft;

        for (int row = 0; row < grid; row++) {
            for (int column = 0; column < grid; column++) {

                if (row == 0 && column == 0) {
                    lastTile = topLeft;
                } else if (column == 0) {
                    Tile tileAbove = view[row - 1][column];
                    Tile tileBelow = tiles.get(findTileWithEdge(tileAbove.bottom));
                    tileBelow.orientUntil(t -> t.top.equals(tileAbove.bottom));
                    lastTile = tileBelow;
                } else {
                    Tile tileToLeft = lastTile;
                    Tile tileToRight = tiles.get(findTileWithEdge(lastTile.right));
                    tileToRight.orientUntil(t -> t.left.equals(tileToLeft.right));
                    lastTile = tileToRight;
                }

                view[row][column] = lastTile;
                remainingTiles.remove(lastTile.tileNumber);

            }
        }

        List<String> combined = new ArrayList<>();

        int tileSize = view[0][0].getContents().size();

        for (int row = 0; row < grid; row++) {
            // each tile line (skipping the top and bottom border rows) becomes one image line
            for (int l = 1; l < tileSize - 1; l++) {
                StringBuilder line = new StringBuilder();
                for (int c = 0; c < grid; c++) {
                    // drop the left and right border columns
                    line.append(view[row][c].getContents().get(l), 1, tileSize - 1);
                }
                combined.add(line.toString());
            }
        }

        return new Tile(1, combined);
    }


    public int findTileWithEdge(String edge) {
        for (Tile tile : remainingTiles.values()) {
            if (tile.possibleSides.contains(edge)) {
                return tile.tileNumber;
            }
        }
        throw new RuntimeException("Could not find the edge");
    }
}

class Tile {

    List<String> contents;
    final int tileNumber;
    String top, right = "", bottom, left = "";
    Set<String> sides = new HashSet<>();
    int edgeMatches;
    Set<String> possibleSides = new HashSet<>();

    public Tile(int tileNumber, List<String> contents) {
        this.tileNumber = tileNumber;
        this.contents = contents;
        determineSides(contents);
        possibleSides.add(top);
        possibleSides.add(right);
        possibleSides.add(bottom);
        possibleSides.add(left);
        possibleSides.add(new StringBuilder(top).reverse().toString());
        possibleSides.add(new StringBuilder(right).reverse().toString());
        possibleSides.add(new StringBuilder(bottom).reverse().toString());
        possibleSides.add(new StringBuilder(left).reverse().toString());

        sides.add(canonicalSide(top));
        sides.add(canonicalSide(right));
        sides.add(canonicalSide(bottom));
        sides.add(canonicalSide(left));

    }

    public List<String> getContents() {
        return contents;
    }

    private void determineSides(List<String> contents) {
        top = contents.getFirst();
        bottom = contents.getLast();
        right = "";
        left = "";
        for (String line : contents) {
            char[] l = line.toCharArray();
            right = right + l[l.length - 1];
            left = left + l[0];
        }
    }

    public static String canonicalSide(String edge) {
        String rev = new StringBuilder(edge).reverse().toString();
        return edge.compareTo(rev) <= 0 ? edge : rev;
    }

    public Set<String> getSides() {
        Set<String> sides = new HashSet<>();
        sides.add(top);
        sides.add(right);
        sides.add(bottom);
        sides.add(left);
        return sides;
    }

    public void setEdgeMatches(int matches) {
        edgeMatches = matches;
    }

    public void rotateTile() {
        int size = contents.size();
        List<String> rotated = new ArrayList<>();
        for (int column = 0; column < size; column++) {
            StringBuilder line = new StringBuilder();
            for (int row = size - 1; row >= 0; row--) {
                line.append(contents.get(row).charAt(column));
            }
            rotated.add(line.toString());
        }
        contents = rotated;
        determineSides(contents);
    }

    public void flipTile() {
        int size = contents.size();
        List<String> flipped = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            StringBuilder line = new StringBuilder();
            for (int column = size - 1; column >= 0; column--) {
                line.append(contents.get(row).charAt(column));
            }
            flipped.add(line.toString());
        }
        contents = flipped;
        determineSides(contents);
    }

    void orientUntil(Predicate<Tile> check) {
        for (int i = 0; i < 8; i++) {
            if (check.test(this)) return;
            rotateTile();
            if (i == 3) flipTile();
        }
        throw new RuntimeException("TILE IS NEVER IN THE RIGHT ORIENTATION");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tile " + tileNumber + ":\n");
        for (String row : contents) {
            sb.append(String.join(" ", row.split(""))).append("\n");
        }
        return sb.toString();
    }

    public int countNessies() {
        // find Nessie

        int seaMonsterCounter = 0;
        for (int row = 0; row <= contents.size() - 3; row++) {
            for (int column = 0; column <= contents.get(0).length() - 20; column++) {

                boolean allMatch = true;
                for (Coordinates coordinate : Nessie.COORDINATES) {
                    if (!(contents.get(row + coordinate.row()).charAt(column + coordinate.column()) == '#')) {
                        allMatch = false;
                    }
                }
                if (allMatch) seaMonsterCounter++;
            }
        }
        return seaMonsterCounter;
    }

    public int getRoughness() {
        int count = 0;
        for (String line : contents) {
            for (char c : line.toCharArray()) {
                if (c == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    public int countWaves() {
        orientUntil(t -> t.countNessies() > 0);
        int nessies = countNessies();
        int roughness = getRoughness();
        List<Coordinates> coordinates = Nessie.COORDINATES;
        return roughness - (nessies * coordinates.size());
    }

}

record Coordinates(int row, int column) {
}

class Nessie {

    static final List<Coordinates> COORDINATES = getNessieCoordinatesList();

    private static List<Coordinates> getNessieCoordinatesList() {
        String row1 = "                  # ";
        String row2 = "#    ##    ##    ###";
        String row3 = " #  #  #  #  #  #   ";

        List<String> seaMonster = new ArrayList<>();
        seaMonster.add(row1);
        seaMonster.add(row2);
        seaMonster.add(row3);

        List<Coordinates> coordinates = new ArrayList<>();
        for (int r = 0; r < seaMonster.size(); r++) {
            for (int c = 0; c < row1.length(); c++) {
                if (seaMonster.get(r).toCharArray()[c] == '#') {
                    coordinates.add(new Coordinates(r, c));
                }
            }
        }
        return coordinates;
    }

}
