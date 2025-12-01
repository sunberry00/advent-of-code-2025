package com.sunberry.aoc.utils;

import java.util.List;

public record Point(int x, int y) {

    public Point add(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
    }

    public List<Point> neighbors() {
        return List.of(
            new Point(x, y - 1), // Up
            new Point(x, y + 1), // Down
            new Point(x - 1, y), // Left
            new Point(x + 1, y)  // Right
        );
    }

    public int manhattanDistance(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }
}