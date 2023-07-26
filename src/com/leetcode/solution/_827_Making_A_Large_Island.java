package com.leetcode.solution;

import java.util.*;

/**
 * 最好情况是四个方向的长条，中间差1格联通
 * T:O(N*N)
 * S:O(N/2*N/2)个岛屿map~O(N*N)
 */

public class _827_Making_A_Large_Island {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int n;

    public int largestIsland(int[][] grid) {
        n = grid.length;
        int res = 0, idx = 2;   // 0是海洋 和 1是陆地，所以从2开始编号

        //将(岛屿索引，岛屿面积)存入map中。
        //即求，编号后，每个岛屿的面积。
        // <index, area>
        Map<Integer, Integer> areaMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    areaMap.put(idx, calculateArea(idx, grid, i, j));
                    idx++;
                }
            }
        }

        // 当遇到一个非岛屿时，则判断上下左右是否连通。
        // Set用来标记已经加入的岛屿,只有未加入的才会
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                Set<Integer> set = new HashSet<>(); //
                if (grid[i][j] == 0) {
                    //上
                    if (i - 1 >= 0 && grid[i - 1][j] != 0 && set.add(grid[i - 1][j])) {
                        sum += areaMap.get(grid[i - 1][j]);
                    }
                    //下
                    if (i + 1 < n && grid[i + 1][j] != 0 && set.add(grid[i + 1][j])) {
                        sum += areaMap.get(grid[i + 1][j]);
                    }
                    //左
                    if (j - 1 >= 0 && grid[i][j - 1] != 0 && set.add(grid[i][j - 1])) {
                        sum += areaMap.get(grid[i][j - 1]);
                    }
                    //右
                    if (j + 1 < n && grid[i][j + 1] != 0 && set.add(grid[i][j + 1])) {
                        sum += areaMap.get(grid[i][j + 1]);
                    }
                    // 最后加上自己的那个点
                    sum++;
                    // 更新res
                    res = Math.max(res, sum);
                }
            }
        }
        // res如果是0，则代表完全没有空格0，全是1，则直接n*n的棋盘大小
        return res == 0 ? n * n : res;
    }

    //BFS计算岛屿面积
    //将连通岛屿格子值赋值为岛屿编号，防止重复计算。
    public int calculateArea(int idx, int[][] grid, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        grid[i][j] = idx;
        int sum = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            sum++;      //计算岛屿面积

            //for循环判断该岛屿上下左右是否是岛屿。如果是岛屿则入队。
            //directions数组中，存的是方向。
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                // 只有是1(岛屿)才能被计算
                if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] != 1)
                    continue;    //如果越界或者不是岛屿则跳过

                grid[x][y] = idx;
                q.offer(new int[]{x, y});
            }
        }
        return sum;
    }
}
