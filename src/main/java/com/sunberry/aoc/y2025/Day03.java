package com.sunberry.aoc.y2025;

import com.sunberry.aoc.Solution;
import com.sunberry.aoc.utils.InputUtils;
import java.util.Arrays;
import java.util.List;

public class Day03 implements Solution {

  private int findMaxJoltage(List<Integer> bank) {
    int firstDigit = -1;
    int secondDigit = -1;
    int firstIndex = -1;
    for (int i = 0; i < bank.size(); ++i) {
      int current = bank.get(i);
      if (firstIndex == -1) {
        if (i != bank.size() - 1) {
          firstDigit = current;
          firstIndex = i;
        }
      } else {
        if (firstDigit < current) {
          if (i != bank.size() - 1) {
            firstDigit = current;
            secondDigit = -1;
          } else {
            if (secondDigit == -1 || secondDigit < current) {
            secondDigit = current;
          }
          }
        } else {
          if (secondDigit == -1 || secondDigit < current) {
            secondDigit = current;
          }
        }
      }
    }
    return firstDigit * 10 + secondDigit;
  }

  @Override
  public Object part1() {
    List<String> banks = InputUtils.readLines("2025", "day03");
    List<List<Integer>> intBanks = banks.stream()
        .map(bank -> Arrays
            .stream(bank.split(""))
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList())
        .toList();


    int sum = intBanks
        .stream()
        .mapToInt(this::findMaxJoltage)
        .sum();

    return sum;
  }

  @Override
  public Object part2() {
    return null;
  }

  public static void main(String[] args) {
    new Day03().run();
  }
}
