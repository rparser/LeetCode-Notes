class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        // add all gates to the queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        // update distance from gates
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] dir : dirs) {
                int X = curPos[0] + dir[0];
                int Y = curPos[1] + dir[1];
                //跳过的选项
                if (X < 0 || Y < 0 || X >= m || Y >= n || rooms[X][Y] != Integer.MAX_VALUE) continue;
                //更新room值
                rooms[X][Y] = rooms[curPos[0]][curPos[1]] + 1;
                //按顺序推入，所以队列顺序为距离gate距离为0001111222223333333
                queue.offer(new int[]{X, Y});
            }
        }
    }

    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    public void dfs(int[][] rooms, int i, int j, int dis) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < dis)
            return;
        rooms[i][j] = dis;
        dfs(rooms, i + 1, j, dis + 1);
        dfs(rooms, i - 1, j, dis + 1);
        dfs(rooms, i, j + 1, dis + 1);
        dfs(rooms, i, j - 1, dis + 1);
    }
}