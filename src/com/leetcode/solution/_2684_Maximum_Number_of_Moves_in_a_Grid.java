package com.leetcode.solution;

public class _2684_Maximum_Number_of_Moves_in_a_Grid {
    //dfs
    private int n;
    private int m;
    private int max = 0;
    //vis用于剪枝操作
    private boolean[][] visited;

    public int maxMoves(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            dfs(i, 0, grid, visited);
            //如果max为m-1，已经是能达到的最大值了，无须再考虑后面的情况了
            if (max == m - 1) {
                break;
            }
        }
        return max;
    }

    public void dfs(int i, int j, int[][] grid, boolean[][] vis) {
        //如果j达到最大值
        if (j == m - 1) {
            //max即为该最大值
            max = m - 1;
            //结束搜索
            return;
        }
        //符合移动条件，且目标单元格还没有搜索过
        if (grid[i][j + 1] > grid[i][j] && !vis[i][j + 1]) {
            //继续搜索
            dfs(i, j + 1, grid, vis);
            //并标记目标单元格已经搜索，避免重复搜索
            vis[i][j + 1] = true;
        }
        //下面同理
        if (i > 0 && grid[i - 1][j + 1] > grid[i][j] && !vis[i - 1][j + 1]) {
            dfs(i - 1, j + 1, grid, vis);
            vis[i - 1][j + 1] = true;
        }
        if (i < n - 1 && grid[i + 1][j + 1] > grid[i][j] && !vis[i + 1][j + 1]) {
            dfs(i + 1, j + 1, grid, vis);
            vis[i + 1][j + 1] = true;
        }
        //全部搜索完毕后，若j值比max大，则保留该j值为最大值
        max = Math.max(max, j);
    }
}
