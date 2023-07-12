package com.leetcode.doordash;

/**
 * 题目要找的是连续递增的序列，且方向只能是上，下，左，右4个方向，所以可以先从一个格子开始找，对比它4周的格子，
 * 有没有比它小的，如果有，比如有A，B，C三个格子都比它小，
 * 那么当前格子的最大连续递增长度就是这3个格子的最大连续递增长度中的最大值+1（有点绕，多读两遍应该就可以理解了），
 * 那么A，B，C的最大长度从哪里来呢，答案肯定是递归去找，直到找到一个比它四周都小的格子，
 * 当前格子长度就定为1，至此，整个思路就缕清了，需要用一个与matrix一样大小的数组来存放每一个格子的最大递增长度
 * if确保递增不会出环，visited判断是否走过
 * O(mn), O(mn)
 */

class _329_矩阵最长递增 {
    private final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //visited有两个作用：1.判断是否访问过，2.存储当前格子的最长递增长度
        int[][] visited = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                //找到下个没有访问过的开始访问
                if (visited[i][j] == 0)
                    max = Math.max(max, dfs(i, j, matrix, visited));

        return max;
    }

    public int dfs(int i, int j, int[][] matrix, int[][] visited) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (visited[i][j] > 0)
            return visited[i][j];

        int max = 0;
        //这里分别去判断4周是否比当前数小，然后去递归遍历
        for (int[] dir : DIRS) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r >= 0 && c >= 0 && r < m && c < n && matrix[r][c] < matrix[i][j])
                max = Math.max(max, dfs(r, c, matrix, visited)); // 是周围四个方向的最大值
        }
        // 到这里说明此格未被访问(到了最深处，一开始为0)
        // +1是因为，即使周围都比自己大，也要加上自己，长度变为1
        visited[i][j] = max + 1; // 这句话是在for loop外边 - 因为必然不能+四次
        return max + 1; // max+1返回给上一层
    }
}
