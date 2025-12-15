package com.sunberry.aoc.y2025;

import com.sunberry.aoc.Solution;
import com.sunberry.aoc.utils.InputUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day05 implements Solution {

  class Range {

    private long minId;
    private long maxId;

    public Range(long minId, long maxId) {
      this.minId = minId;
      this.maxId = maxId;
    }

    public boolean isIdInRange(long id) {
      return id >= minId && id <= maxId;
    }

    public boolean isRangeWhollyWithin(Range range) {
      return range.minId >= this.minId && range.maxId <= this.maxId;
    }

    public boolean isRangeFullyOut(Range range) {
      return range.minId <= this.minId && range.maxId >= this.maxId;
    }

    public Range processRanges(Range range) {
      if (this.isRangeWhollyWithin(range)) {
        return this;
      } else if (this.isRangeFullyOut(range)) {
        return range;
      } else {
        if (this.minId <= range.maxId && this.minId >= range.minId) {
          return new Range(range.minId, this.maxId);
        } else {
          return new Range(this.minId, range.maxId);
        }
      }
    }

    public long countId() {
      return maxId - minId + 1;
    }

  }

  List<Range> ranges = new ArrayList<>();
  List<Long> ingredients = new ArrayList<>();

  private void processInput(List<String> input) {
    for (String line : input) {
      if (line.contains("-")) {
        String[] range = line.split("-");
        long minId = Long.parseLong(range[0]);
        long maxId = Long.parseLong(range[1]);
        ranges.add(new Range(minId, maxId));
      } else if (!line.isEmpty()) {
        ingredients.add(Long.parseLong(line));
      }
    }
  }

  @Override
  public Object part1() {
    List<String> input = InputUtils.readLines("2025", "day05", "_test");
    processInput(input);
    int count = 0;
    for (Long ingredient : ingredients) {
      if (ranges.stream().anyMatch(range -> range.isIdInRange(ingredient))) {
        count++;
      }
    }
    return count;
  }

  private boolean isRangeWhollyWithin(Range firstRange, Range secondRange) {
    if (firstRange.equals(secondRange)) {
      return false;
    }
    if (firstRange.minId >= secondRange.minId && firstRange.maxId <= secondRange.maxId) {
      return true;
    }
    if (firstRange.minId <= secondRange.minId && firstRange.maxId >= secondRange.maxId) {
      return true;
    }
    return false;
  }

  private Range processWhollyWithinRanges(Range firstRange, Range secondRange) {
    if (firstRange.minId >= secondRange.minId && firstRange.maxId <= secondRange.maxId) {
      return secondRange;
    } else {
      return firstRange;
    }
  }

  private boolean isRangesIntersect(Range firstRange, Range secondRange) {
    if (firstRange.equals(secondRange)) {
      return false;
    }
    if (firstRange.maxId >= secondRange.minId && firstRange.minId <= secondRange.minId && firstRange.maxId <= secondRange.maxId) {
      return true;
    }
    if (secondRange.maxId >= firstRange.minId && secondRange.minId <= firstRange.minId && secondRange.maxId <= firstRange.maxId) {
      return true;
    }
    return false;
  }

  private Range processIntersectRanges(Range firstRange, Range secondRange) {
    if (firstRange.maxId >= secondRange.minId && firstRange.minId <= secondRange.minId && firstRange.maxId <= secondRange.maxId) {
      return new Range(firstRange.minId, secondRange.maxId);
    } else {
      return new Range(secondRange.minId, firstRange.maxId);
    }
  }

@Override
  public Object part2() {
    this.ranges = new ArrayList<>();
    this.ingredients = new ArrayList<>();

    List<String> input = InputUtils.readLines("2025", "day05");
    processInput(input);

    if (ranges.isEmpty()) {
      return 0L;
    }

    ranges.sort(Comparator.comparingLong(r -> r.minId));

    List<Range> mergedRanges = new ArrayList<>();
    Range current = ranges.get(0);

    for (int i = 1; i < ranges.size(); i++) {
      Range next = ranges.get(i);
      if (current.maxId >= next.minId) {
        current.maxId = Math.max(current.maxId, next.maxId);
      } else {
        mergedRanges.add(current);
        current = next;
      }
    }
    mergedRanges.add(current);

    long sum = 0;
    for (Range range : mergedRanges) {
      sum += range.countId();
    }
    return sum;
  }

  public static void main(String[] args) {
    new Day05().run();
  }

}


