class Solution {
    private static int[][] dirs = {{0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) return -1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        grid[0][0] = 1; // 直接用grid[i][j]记录从起点到这个点的最短路径长。按照题意 起点也有长度1
        while (!q.isEmpty() && grid[grid.length - 1][grid[0].length - 1] == 0) { // 求最短路径 使用BFS
            int[] cur = q.poll();
            int curLen = grid[cur[0]][cur[1]];
            for (int[] dir : dirs) {
                int nRow = cur[0] + dir[0];
                int nCol = cur[1] + dir[1];
                if (nRow >= 0 && nCol >= 0 && nRow < grid.length && nCol < grid[0].length && grid[nRow][nCol] == 0) {
                    q.add(new int[]{nRow, nCol});
                    grid[nRow][nCol] = curLen + 1; // 下一个点的路径长度要+1
                }
            }
        }
        // 如果最后终点的值还是0，说明没有到达
        if (grid[grid.length - 1][grid[0].length - 1] != 0) return grid[grid.length - 1][grid[0].length - 1];
        else return -1;
    }
}