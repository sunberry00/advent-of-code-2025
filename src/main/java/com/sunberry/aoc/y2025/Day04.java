package com.sunberry.aoc.y2025;

import com.sunberry.aoc.Solution;
import com.sunberry.aoc.utils.InputUtils;
import java.util.List;

public class Day04 implements Solution {

  private int getNeighbours(int x, int y, List<String> grid) {
    int count = 0;
    if (x != 0) {
      if (y != 0) {
        if (grid.get(x - 1).charAt(y - 1) == '@') { // NW
          count++;
        }
      }
      if (grid.get(x - 1).charAt(y) == '@') { // N
        count++;
      }
      if (y != grid.get(x).length() - 1) {
        if (grid.get(x - 1).charAt(y + 1) == '@') { // NE
          count++;
        }
      }
    }
    if (y != 0) {
      if (grid.get(x).charAt(y - 1) == '@') { // W
        count++;
      }
    }
    if (y != grid.get(x).length() - 1) {
      if (grid.get(x).charAt(y + 1) == '@') { // E
        count++;
      }
    }
    if (x != grid.size() - 1) {
      if (y != 0) {
        if (grid.get(x + 1).charAt(y - 1) == '@') { // SW
          count++;
        }
      }
      if (grid.get(x + 1).charAt(y) == '@') { // S
        count++;
      }
      if (y != grid.get(x).length() - 1) {
        if (grid.get(x + 1).charAt(y + 1) == '@') { // SE
          count++;
        }
      }
    }
    return count;
  }

  @Override
  public Object part1() {
    List<String> grid = InputUtils.readLines("2025", "day04");
    int count = 0;
    for (int i = 0; i < grid.size(); ++i) {
      for (int j = 0; j < grid.get(i).length(); ++j) {
        char current = grid.get(i).charAt(j);
        if (current == '@') {
          int n = getNeighbours(i, j, grid);
          if (n < 4) {
            count++;
          }
        }
      }
    }
    return count;
  }

  @Override
  public Object part2() {
    return null;
  }

  public static void main(String[] args) {
    new Day04().run();
  }
}
