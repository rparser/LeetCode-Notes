class Solution {
    // graph[i]表示图中与节点i相连的所有节点
    //本实现使用一个int数组表示每个顶点的染色情况：0 表示未被染色， 1表示染成黑色 2表示染成白色。
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) return false;
        int v = graph.length;
        int[] colors = new int[v];  // 0未被染色， 1-green 2-red
        // 要考虑非连通图, 所以要遍历每一个结点
        for (int i = 0; i < v; i++)
            // lastColor为0
            if (!dfs(graph, i, colors, 0))
                return false;

        return true;
    }

    private boolean dfs(int[][] graph, int i, int[] colors, int lastColor) {
        // 注意，被染色的就不要继续染色了（因为这是自底向上的，被染色的点，其相连的节点肯定被染色了）
        // 如果继续对被染色的节点染色，就会导致死循环
        if (colors[i] != 0) return colors[i] != lastColor;
        // 未被染色(come from last color)，染成与相邻结点不同的颜色（lastColor为0时，就染成1）
        if (lastColor == 1) colors[i] = 1;
        else colors[i] = 2;
        // graph[i]表示图中与节点i相连的所有节点
        for (int j = 0; j < graph[i].length; j++) {
            if (!dfs(graph, graph[i][j], colors, colors[i])) return false;
        }
        return true;
    }
}