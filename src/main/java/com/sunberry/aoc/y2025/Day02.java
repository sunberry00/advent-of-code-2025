package com.sunberry.aoc.y2025;

import static java.lang.Long.parseLong;

import com.sunberry.aoc.Solution;
import com.sunberry.aoc.utils.InputUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day02 implements Solution {

  class Range {

    String firstID;
    String secondID;

    Range(String firstID, String secondID) {
      this.firstID = firstID;
      this.secondID = secondID;
    }


  }

  private boolean isInvalidId(String id) {
    int length = id.length();
    List<Integer> dividers = IntStream
        .range(1, length / 2 + 1)
        .filter(x -> length % x == 0)
        .boxed()
        .toList();

    for (int divider : dividers) {
      String newId = id.substring(divider).repeat(length / divider);
      if (newId.equals(id)) {
        return true;
      }
    }

    return false;

  }


  @Override
  public Object part1() {
    String input = InputUtils.readString("2025", "day02");
    List<Range> ranges = Arrays.stream(input.split(","))
        .map(idRange -> idRange.split("-"))
        .map(ids -> new Range(ids[0].strip(), ids[1].strip()))
        .toList();

    long sum = 0;
    for (Range range : ranges) {
      for (long i = Long.parseLong(range.firstID); i <= Long.parseLong(range.secondID); ++i) {
        String id = String.valueOf(i);
        if (isInvalidId(id)) {
          sum += Long.parseLong(id);
        }
      }
    }

    return sum;
  }

  @Override
  public Object part2() {
    return null;
  }

  public static void main(String[] args) {
    new Day02().run();
  }

}
