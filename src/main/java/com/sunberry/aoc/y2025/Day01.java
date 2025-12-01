package com.sunberry.aoc.y2025;

import com.sunberry.aoc.Solution;
import com.sunberry.aoc.utils.InputUtils;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;

public class Day01 implements Solution {

  @Override
  public Object part1() {
    List<String> rotationList = InputUtils.readLines("2025", "day01");
    AtomicInteger result = new AtomicInteger();
    rotationList
        .stream()
        .map(x -> x.charAt(0) == 'L' ? (-1) * Integer.parseInt(x.substring(1)) : Integer.parseInt(x.substring(1)))
        .reduce(50, (x, y) -> {
          int tmp = x + y;
          if (tmp > 99 || tmp < 0) {
            tmp %= 100;
          }
          if (tmp == 0) {
            result.addAndGet(1);
          }
          return tmp;
        });
    return result.get();
  }

  @Override
  public Object part2() {
    List<String> rotationList = InputUtils.readLines("2025", "day01");
    AtomicInteger result = new AtomicInteger();
    rotationList
        .stream()
        .map(x -> x.charAt(0) == 'L'
            ? (-1) * Integer.parseInt(x.substring(1))
            : Integer.parseInt(x.substring(1)))
        .reduce(50, (x, y) -> {
          if (Math.abs(y) > 99) {
            result.addAndGet(Math.abs(y) / 100);
            y %= 100;
          }
          int tmp = x + y;
          if (x < 100 && x != 0 && (tmp > 100 || tmp < 0)) {
            result.addAndGet(1);
          }
          if (tmp > 99 || tmp < 0) {
            tmp %= 100;
          }
          if (tmp == 0) {
            result.addAndGet(1);
          }
          return tmp >= 0 ? tmp : tmp + 100;
        });
    return result.get();
  }

  public static void main(String[] args) {
    new Day01().run();
  }
}
