class Solution {
    public int minKnightMoves(int x, int y) {
        // 遍历日字的方向，每轮round++，一旦达到，就停止
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {500, 500});
        int[][] directions = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        int count = 0;
        boolean[][] visited = new boolean[1000][1000];
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                int[] position = queue.poll();
                int r = position[0];
                int c = position[1];
                visited[r][c] = true;
                if (r == x + 500 && c == y + 500) {
                    flag = true;
                    break;
                }

                for (int[] dir : directions) {
                    int a = r + dir[0];
                    int b = c + dir[1];
                    if (!visited[a][b]) {
                        visited[a][b] = true;
                        queue.add(new int[] {a, b});
                    }
                }
            }
            if (flag) {
                break;
            }
        }
        return count - 1;
    }
}