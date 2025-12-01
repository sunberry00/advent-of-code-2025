package com.sunberry.aoc;

public interface Solution {
    Object part1();
    Object part2();

    default void run() {
        long start = System.currentTimeMillis();
        System.out.println("Part 1: " + part1());
        System.out.println("Part 1 Time: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println("Part 2: " + part2());
        System.out.println("Part 2 Time: " + (System.currentTimeMillis() - start) + "ms");
    }
}