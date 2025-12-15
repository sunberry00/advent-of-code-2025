package com.sunberry.aoc.y2025;

import com.sunberry.aoc.Solution;
import com.sunberry.aoc.utils.InputUtils;
import java.util.ArrayList;
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

  @Override
  public Object part2() {
    return null;
  }

  public static void main(String[] args) {
    new Day06().run();
  }
}
