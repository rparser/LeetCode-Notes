package com.leetcode.solution;

class _785_Is_Graph_Bipartite {
    // graph[i]表示图中与节点i相连的所有节点
    //本实现使用一个int数组表示每个顶点的染色情况：0 表示未被染色， 1表示染成黑色 2表示染成白色。
    // 如果edge发现edge两边
    //O(N+M),O(N)
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0)
            return false;
        int v = graph.length;
        int[] colors = new int[v];  // 0未被染色， 1-green 2-red
        // 要考虑非连通图, 所以要遍历每一个结点
        for (int i = 0; i < v; i++)
            // lastColor为0, 如果找到了颜色相同的点，则不是二分图
            if (!dfs(graph, i, colors, 0))
                return false;

        return true;
    }

    private boolean dfs(int[][] graph, int i, int[] colors, int lastColor) {
        // 如果已经染色且和lastcolor相同，则返回false
        if (colors[i] != 0)
            return colors[i] != lastColor;
        // 未被染色(come from last color)，染成与相邻结点不同的颜色（lastColor为0时，就染成1）
        if (lastColor == 1)
            colors[i] = 2;
            // 为0或2时，这个点染成1
        else
            colors[i] = 1;
        //
        for (int j = 0; j < graph[i].length; j++)
            if (!dfs(graph, graph[i][j], colors, colors[i]))
                return false;

        return true;
    }
}