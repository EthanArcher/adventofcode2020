package utils;

import day01.Day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public static List<String> readFileToString(String filename){
        InputStream raw = Day01.class.getClassLoader().getResourceAsStream(filename);
        if (raw == null) {
            System.out.println("Resource not found: " + filename);
            throw new IllegalArgumentException("Resource not found: " + filename);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(raw, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            throw new IllegalArgumentException("Error reading file: " + filename, e);
        }
    }

    public static char[][] readFileToCharGrid(String filename) {
        List<String> lines = readFileToString(filename);
        if (lines.isEmpty()) {
            return new char[0][0];
        }

        char[][] grid = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }
        return grid;
    }

}
