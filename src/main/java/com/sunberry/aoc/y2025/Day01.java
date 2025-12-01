package com.sunberry.aoc.y2025;

import com.sunberry.aoc.Solution;
import com.sunberry.aoc.utils.InputUtils;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day01 implements Solution {

  @Override
  public Object part1() {
    List<String> rotationList = InputUtils.readLines("2025", "day01");
    AtomicInteger result = new AtomicInteger();
    int processingStream = rotationList
        .stream()
        .map(x -> x.charAt(0) == 'L' ? (-1) * Integer.parseInt(x.substring(1)) : Integer.parseInt(x.substring(1)))
        .reduce(50, (x, y) -> {
          int tmp = x + y;
          if (tmp > 99) {
            tmp %= 100;
          }
          if (tmp < 0) {
            tmp %= 100;
          }
          if (tmp == 0) {
            result.addAndGet(1);
          }
          return tmp;
        });
    System.out.println(result.get());
    return result.get();
  }

  @Override
  public Object part2() {
    return null;
  }

  public static void main(String[] args) {
    new Day01().run();
  }
}
