package com.leetcode.solution;

import java.util.*;

/*
 * 制作一个graph，1，把所有edge全放进去
 * 2,把edges按照第二个值排序 (可以不排序，排序能提高效率)
 * 所以edges变成"小"->"大"，且按"小"排序，如果"小"相同按"大"排序
 * 如果存在第三个点，则第三个点的set一定包含第一二个点
 * 三个点的set数目总和-6(除去互相排序的edge数目3x2)则为额外的点的数目
 * 更新
 * O(n^2+nlogn),O(n^2)
 */

public class _1761_Minimum_Degree_of_a_Connected_Trio_in_a_Graph {
    public int minTrioDegree(int n, int[][] edges) {
        int minimumDegree = Integer.MAX_VALUE;

        // 制作所有路径的graph(从a->所有其他相连的点)
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.computeIfAbsent(e[0], x -> new HashSet<>()).add(e[1]);
            graph.computeIfAbsent(e[1], x -> new HashSet<>()).add(e[0]);
        }

        // arrange the array edges such that if e[0] > e[1], swap e[1] and e[0]
        for (int[] e : edges) {
            if (e[0] > e[1]) {
                int temp = e[0];
                e[0] = e[1];
                e[1] = temp;
            }
        }
        // 实际可以不用排序 - 但排序能提升效率
        // 比较各个边，先比e[0](x) 再比 e[1](y)，所以是1-2,1-3,2-3
        Arrays.sort(edges, (e1, e2) -> e1[0] != e2[0] ? e1[0] - e2[0] : e1[1] - e2[1]);

        for (int[] e : edges) {
            int first = e[0];
            int second = e[1];
            for (int i = second + 1; i <= n; i++) { // 检查有没有第三个点i
                // 需要符合如下两个条件
                // 1, 这点在graph里存在，才可能相连
                // 2, 这点在graph里指向了1和2
                // 如果存在，则为额外的点的数目为三个set的大小之和-6(3*2)
                if (graph.containsKey(i) && graph.get(i).contains(first) && graph.get(i).contains(second)) {
                    int currentDegree = graph.get(first).size() + graph.get(second).size() + graph.get(i).size() - 6;
                    if (currentDegree == 0)
                        return 0;

                    minimumDegree = Math.min(minimumDegree, currentDegree);
                }
            }
        }
        // for the last check if our minimumDegree is still Integer.MAX_Value return -1 else return minimumDegree
        return minimumDegree == Integer.MAX_VALUE ? -1 : minimumDegree;
    }
}
