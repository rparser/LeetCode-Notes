class Solution {
    public int[][] generateMatrix(int n) {
        //右、下、左、上
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //当前正在记录的数字
        int i = 1;
        //x、y：当前坐标，dir：当前方向处于方向数组中的下标值
        int x = 0, y = 0, dir = 0;
        int[][] matrix = new int[n][n];
        while (i <= n * n) {
            matrix[x][y] = i;
            int newX = x + direction[dir][0];
            int newY = y + direction[dir][1];
            //需要改变方向的情况
            if (newX >= n || newX < 0 || newY >= n || newY < 0 || matrix[newX][newY] != 0) {
                dir = (dir == 3) ? 0 : dir + 1;
            }
            x += direction[dir][0];
            y += direction[dir][1];
            i++;
        }
        return matrix;
    }
}
