class Solution {
    List<Integer>[] graph;

    public int countComponents(int n, int[][] edges) {
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList();
        }
        int count = 0;
        buildGraph(edges);
        // dfs
        boolean[] mark = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!mark[i]) {
                dfs(i, graph, mark);
                count++;
            }
        }
        return count;
    }

    public void buildGraph(int[][] edges) {
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
    }

    public void dfs(int u, List<Integer>[] graph, boolean[] mark) {
        if (mark[u]) {
            return;
        }
        mark[u] = true;
        for (Integer v : graph[u]) {
            dfs(v, graph, mark);
        }
    }
}
