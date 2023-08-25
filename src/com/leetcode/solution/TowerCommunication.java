package com.leetcode.solution;

import java.util.*;
import java.util.List;

public class TowerCommunication {
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<int[]> findCommunicatingTowers(char[][] grid) {
        List<int[]> result = new ArrayList<>();
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 't') {
                    bfs(grid, i, j, result);
                }
            }
        }

        return result;
    }

    private void bfs(char[][] grid, int startX, int startY, List<int[]> result) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new int[]{startX, startY});
        visited.add(startX + "#" + startY);

        while (!queue.isEmpty()) {
            int[] tower = queue.poll();
            int x = tower[0];
            int y = tower[1];

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                        && grid[newX][newY] == 'o' && !visited.contains(newX + "#" + newY)) {
                    queue.offer(new int[]{newX, newY});
                    visited.add(newX + "#" + newY);
                    result.add(new int[]{startX, startY, newX, newY});
                }
            }
        }
    }

    private void bfs2(char[][] grid, int startX, int startY, List<int[]> result) {
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new int[]{startX, startY});
        visited.add(startX + "#" + startY);

        while (!queue.isEmpty()) {
            int[] tower = queue.poll();
            int x = tower[0];
            int y = tower[1];

            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                        && !visited.contains(newX + "#" + newY)) {
                    queue.offer(new int[]{newX, newY});
                    visited.add(newX + "#" + newY);
                    result.add(new int[]{startX, startY, newX, newY});
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'t', 'o', 't', 'o'},
                {'o', 'm', 'o', 'o'},
                {'t', 'o', 'o', 't'},
                {'o', 'o', 'o', 'o'}
        };

        TowerCommunication solution = new TowerCommunication();
        List<int[]> result = solution.findCommunicatingTowers(grid);

        for (int[] tower : result) {
            System.out.println(Arrays.toString(tower));
        }
    }
}
