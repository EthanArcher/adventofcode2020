package utils;

import day01.Day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public static List<String> readFileToString(String filename){
        InputStream raw = Day01.class.getClassLoader().getResourceAsStream(filename);
        if (raw == null) {
            System.out.println("Resource not found: " + filename);
            return Collections.emptyList();
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(raw, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            return Collections.emptyList();
        }
    }

}
