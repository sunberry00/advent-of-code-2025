package com.sunberry.aoc.y2025;

import com.sunberry.aoc.Solution;
import com.sunberry.aoc.utils.InputUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day06 implements Solution {

  @Override
  public Object part1() {
    List<String> input = InputUtils.readLines("2025", "day06");
    List<String[]> splitInput = input.stream().map(x -> x.strip().split(" +")).toList();
    List<Long> results = new ArrayList<>();
    for (int i = 0; i < splitInput.getFirst().length; ++i) {
      long res = splitInput.getLast()[i].equals("*") ? 1 : 0;
      for (int j = 0; j < splitInput.size() - 1; ++j) {
        long currentLong = Long.parseLong(splitInput.get(j)[i]);
        res = Objects.equals(splitInput.getLast()[i], "*") ? res * currentLong : res + currentLong;
      }
      results.add(res);
    }
    return results.stream().mapToLong(Long::longValue).sum();

  }

  private String collectNumber(List<String> grid, int index) {
    StringBuilder number = new StringBuilder();
    for (int i = 0; i < grid.size(); ++i) {
      char currentChar = grid.get(i).length() <= index ? ' ' : grid.get(i).charAt(index);
      number.append(currentChar);
    }
    return number.toString();
  }

  @Override
  public Object part2() {
    List<String> grid = InputUtils.readLines("2025", "day06");
    int firstIndex = grid.stream().mapToInt(String::length).max().getAsInt() - 1;
    List<Long> results = new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();
    for (int i = firstIndex; i >= 0; --i) {
      String currentNumber = collectNumber(grid, i);
      if (currentNumber.isBlank()) {
        continue;
      }
      if (!currentNumber.contains("+") && !currentNumber.contains("*")) {
        numbers.add(Integer.parseInt(currentNumber.strip()));
      } else {
        char operator = currentNumber.charAt(currentNumber.length() - 1);
        currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        numbers.add(Integer.parseInt(currentNumber.strip()));
        if (operator == '+') {
          results.add(numbers.stream().mapToLong(x -> x).sum());
          numbers = new ArrayList<>();
        } else {
          results.add(numbers.stream().mapToLong(Integer::longValue).reduce(1, (x, y) -> x * y));
          numbers = new ArrayList<>();
        }
      }
    }
    return results.stream().mapToLong(x -> x).sum();
  }

  public static void main(String[] args) {
    new Day06().run();
  }
}
