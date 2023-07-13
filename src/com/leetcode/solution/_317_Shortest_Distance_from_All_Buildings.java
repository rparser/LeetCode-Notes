package com.leetcode.solution;

import java.util.LinkedList;
import java.util.Queue;

/*
 *  思路：
 * (1) 从每一个建筑物开始进行BFS
 * (2) 在搜索的同时计算每一个空格到这个建筑物的距离
 * (3) 在搜索的同时将每一个空格到每一个建筑物的距离进行累加，得到每个空格到所有建筑物的距离
 * (4) 取空格到所有建筑物的最小距离

 * 访问每个建筑物（比如5个） -> 空格到这个建筑物的距离 -> 累加 （2，3一共运行5次） ->更新距离最小的空格
 * 0代表空地，1代表建筑物，2代表不能建造
 * Time Complexity: O(M^2 * N^2)，
 * Space Complexity: O(mn * k) k为1的数目
 */

class _317_Shortest_Distance_from_All_Buildings {
    private final int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;

        int[][] totalDist = new int[m][n];
        int result = Integer.MAX_VALUE;
        // 用于标记空地被访问几次，visitedCounter最后会是现有建筑物1的数目（3个1就是-3) - 负数是为了不影响正数标记: 1是建筑物2是障碍
        // 在bfs的过程中，如果visitedCounter数量不对，则无须继续计算了，说明之前有建筑物无法访问此空地
        int visitedCounter = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                // (1) 从每一个建筑物开始进行BFS - 因为建筑物数量小于空地数量
                if (grid[i][j] == 1) {
                    result = bfs(grid, i, j, visitedCounter, totalDist); // result每次都会增大
                    visitedCounter--; // 最后会是建筑物1的数量
                }
        // System.out.println(Arrays.deepToString(grid));
        return result;
    }

    private int bfs(int[][] grid, int i, int j, int visitedCounter, int[][] totalDist) {
        int res = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        // 队列中每个数组有 3 个元素，分别表示：
        // 第一个元素和第二个元素表示坐标值
        // 第三个元素表示当前坐标到建筑物的距离,初始从当前建筑物到当前建筑物的距离是 0
        queue.add(new int[]{i, j, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            // 队列取出当前i,j的距离
            int currDist = curr[2];
            for (int[] dir : dirs) {
                int row = curr[0] + dir[0];
                int col = curr[1] + dir[1];
                // grid[row][col] == visitedCounter表示这round未被访问
                // 2障碍不会被访问到，因为2不会等于负数
                if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == visitedCounter) {
                    if (grid[row][col] == 2)
                        continue;
                    // (2) 在搜索的同时计算每一个空格到这个建筑物的距离
                    int dist = currDist + 1;
                    // (3) 在搜索的同时将每一个空格到每一个建筑物的距离进行累加
                    totalDist[row][col] += dist;
                    // (4) 取空格到所有建筑物的最小距离 - 历史最低和当前值相比
                    res = Math.min(res, totalDist[row][col]);

                    queue.add(new int[]{row, col, dist});
                    // 和 visitedCounter 标识对应
                    grid[row][col]--;
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
