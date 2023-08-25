package com.leetcode.solution;

import java.util.*;

/**
 * 这是最简单的思路。 1.完善好邻接表。 2.中心扩散
 * O(N)~O(N)
 */

class _863_All_Nodes_Distance_K_in_Binary_Tree {
    Map<TreeNode, TreeNode> node_parent = new HashMap<>();

    public void dfs_find_node_parent(TreeNode node) {
        if (node != null) {
            if (node.left != null) {
                node_parent.put(node.left, node);
            }
            if (node.right != null) {
                node_parent.put(node.right, node);
            }
            dfs_find_node_parent(node.left);
            dfs_find_node_parent(node.right);
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs_find_node_parent(root);

        List<Integer> res = new ArrayList<>();
        if (k == 0) {
            res.add(target.val);
        }

        //------------------- bfs波纹法，先visit和先判只适应于 0 < k的情况
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int level = 0;
        while (!queue.isEmpty() && level < k) {
            level++;
            int cur_len = queue.size();
            for (int i = 0; i < cur_len; i++) {
                TreeNode x = queue.peek();
                queue.poll();
                List<TreeNode> ys = new ArrayList<>();
                if (node_parent.containsKey(x)) {
                    ys.add(node_parent.get(x));
                }
                ys.add(x.left);
                ys.add(x.right);
                for (TreeNode y : ys) {
                    if (y != null && !visited.contains(y)) {
                        if (level == k) {
                            res.add(y.val);
                        }
                        visited.add(y);
                        queue.offer(y);
                    }
                }
            }
        }
        return res;
    }
}
