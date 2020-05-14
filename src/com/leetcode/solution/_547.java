//时间复杂度为O(n^2)O(n

public class findCircleNum {
    public int findCircleNum(int[][] M) {
        int count = 0;
        n = M.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                visited[i] = true;
                dfs(M, i, visited);
            }
        }
        return count;
    }

    private void dfs(int[][] M, int v, boolean[] visited) {
        for (int i = 0; i < n; i++) {
            if (!visited[i] && M[v][i] == 1) {
                visited[i] = true;
                dfs(M, i, visited);
            }
        }
    }
}
