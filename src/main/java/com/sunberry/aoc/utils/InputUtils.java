package com.sunberry.aoc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InputUtils {

    private static final String TEST_SUFFIX = "_test";

    public static List<String> readLines(String year, String day) {
        return readLines(year, day, "");
    }

    public static List<String> readTestLines(String year, String day) {
        return readLines(year, day, TEST_SUFFIX);
    }

    public static List<String> readLines(String year, String day, String suffix) {
        try {
            return Files.readAllLines(getPath(year, day, suffix));
        } catch (IOException e) {
            throw new RuntimeException("Could not read input file: " + getPath(year, day, suffix), e);
        }
    }

    public static String readString(String year, String day) {
        return readString(year, day, "");
    }

    public static String readTestString(String year, String day) {
        return readString(year, day, TEST_SUFFIX);
    }

    public static String readString(String year, String day, String suffix) {
        try {
            return Files.readString(getPath(year, day, suffix));
        } catch (IOException e) {
            throw new RuntimeException("Could not read input file: " + getPath(year, day, suffix), e);
        }
    }

    private static Path getPath(String year, String day, String suffix) {
        String fileName = day + suffix + ".txt";
        return Paths.get("src", "main", "resources", "inputs", year, fileName);
    }
}