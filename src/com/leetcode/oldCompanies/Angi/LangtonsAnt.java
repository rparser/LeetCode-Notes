package com.leetcode.oldCompanies.Angi;

import java.util.ArrayList;
import java.util.List;

public class LangtonsAnt {
    static class Ant {
        int x;
        int y;
        int direction;  // 0: North, 1: East, 2: South, 3: West

        public Ant(int x, int y) {
            this.x = x;
            this.y = y;
            this.direction = 0;  // Start facing North
        }

        public void move(boolean clockwise) {
            if (clockwise) {
                direction = (direction + 1) % 4;
            } else {
                direction = (direction - 1 + 4) % 4;
            }

            switch (direction) {
                case 0 -> // North
                        y++;
                case 1 -> // East
                        x++;
                case 2 -> // South
                        y--;
                case 3 -> // West
                        x--;
            }
        }
    }

    public static List<List<Integer>> simulateLangtonsAnt(List<List<Boolean>> grid, int steps) {
        int center = grid.size() / 2;
        Ant ant = new Ant(center, center);
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(center, center));
        ant.y++;
        grid.get(center).set(center, !grid.get(center).get(center));

        for (int step = 0; step < steps; step++) {
            boolean clockwise = grid.get(ant.x).get(ant.y);
            int oldX = ant.x;
            int oldY = ant.y;

            // Move the ant
            ant.move(clockwise);

            // Add ant's position to the result
            result.add(List.of(oldX, oldY));

            // Flip the color of the cell at the ant's position
            grid.get(oldX).set(oldY, !grid.get(oldX).get(oldY));
        }
        return result;
    }

    public static void main(String[] args) {
        // Define grid and steps
        int gridSize = 3;  // Size of the grid (5x5 in this example)
        List<List<Boolean>> grid = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < gridSize; j++) {
                row.add(true);  // Initialize all cells to true
            }
            grid.add(row);
        }
        int steps = 6;  // Number of steps to simulate

        List<List<Integer>> result = simulateLangtonsAnt(grid, steps);

        // Print the result
        for (List<Integer> coordinates : result) {
            System.out.println("(" + coordinates.get(0) + ", " + coordinates.get(1) + ")");
        }
    }
}
