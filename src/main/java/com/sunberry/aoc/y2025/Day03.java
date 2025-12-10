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

    return intBanks
        .stream()
        .mapToInt(this::findMaxJoltage)
        .sum();
  }

  private long calcSum(int[] batteries) {
    long sum = 0;
    for (int i = 0; i < 12; ++i) {
      sum += (long) (Math.pow(10, 11 - i) * batteries[i]);
    }
    return sum;
  }

  private long findTwelveBiggestJoltage(List<Integer> bank) {
    int[] batteries = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[] batIndex = {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    for (int i = 0; i < 12; ++i) {
      for (int j = i == 0 ? i : batIndex[i - 1] + 1; j < bank.size() - 11 + i; ++j) {
        int current = bank.get(j);
        if (current > batteries[i]) {
          batteries[i] = current;
          batIndex[i] = j;
        }
      }
    }
    long r = calcSum(batteries);
    return r;
  }

  @Override
  public Object part2() {
    List<String> banks = InputUtils.readLines("2025", "day03");
        List<List<Integer>> intBanks = banks.stream()
        .map(bank -> Arrays
            .stream(bank.split(""))
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList())
        .toList();

        return intBanks.stream().mapToLong(this::findTwelveBiggestJoltage).sum();
  }

  public static void main(String[] args) {
    new Day03().run();
  }
}
