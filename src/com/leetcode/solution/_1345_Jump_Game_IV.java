package com.leetcode.solution;

import java.util.*;

public class _1345_Jump_Game_IV {
    public int minJumps(int[] arr) {
        // 可以看成一张无向图，每个元素与它相邻的元素相连，同时还与它值相同的元素相连
        // 使用BFS从0搜索到n-1即可

        int n = arr.length;
        if (n == 1) {
            return 0;
        }

        // 记录相同值的元素有哪些下标
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }

        // BFS使用的队列
        Queue<Integer> queue = new LinkedList<>();
        // 记录已经访问过的元素
        // 数组范围：1 <= arr.length <= 5 * 10^4
        // 使用数组比Set效率更优
        // 使用位图更优
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;

        int ans = 0;
        while (!queue.isEmpty()) {
            // 与二叉树的层序遍历类似
            int size = queue.size();
            while (size-- > 0) {
                int index = queue.poll();

                // 满足条件，直接返回
                if (index == n - 1) {
                    return ans;
                }

                // 把它相边的直接入队
                if (map.containsKey(arr[index])) {
                    for (int next : map.get(arr[index])) {
                        if (next != index && !visited[next]) {
                            queue.offer(next);
                            visited[next] = true;
                        }
                        // 剪枝：这个元素相连的都处理过了，后面再遍历到再处理肯定都已经访问过了，不如直接移除，可以减少遍历的次数
                        map.remove(arr[index]);
                    }
                }

                if (index + 1 <= n - 1 && !visited[index + 1]) {
                    queue.offer(index + 1);
                    visited[index + 1] = true;
                }

                if (index - 1 >= 0 && !visited[index - 1]) {
                    queue.offer(index - 1);
                    visited[index - 1] = true;
                }
            }
            ans++;
        }

        // 不会走到这里
        return -1;
    }
}
