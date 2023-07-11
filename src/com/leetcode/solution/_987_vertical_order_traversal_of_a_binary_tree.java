package com.leetcode.solution;

import java.util.*;

class _987_vertical_order_traversal_of_a_binary_tree {
    static class TreeNodeWithPosition {
        TreeNode node;
        int level;
        int dist;

        public TreeNodeWithPosition(TreeNode node, int level, int dist) {
            this.node = node;
            this.level = level;
            this.dist = dist;
        }
    }

    // O(NlogN), O(N)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNodeWithPosition> queue = new LinkedList<>();
        TreeNodeWithPosition node = new TreeNodeWithPosition(root, 0, 0);
        queue.add(node);
        HashMap<Integer, List<TreeNodeWithPosition>> map = new HashMap<>();

        //记录最左最右
        int min = 0, max = 0;
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //两个queue分别取出node和dist
                TreeNodeWithPosition cur = queue.poll();
                int dist = cur.dist;
                if (!map.containsKey(dist))
                    map.put(dist, new ArrayList<>());

                map.get(dist).add(new TreeNodeWithPosition(cur.node, level, dist));
                // 左子树同时加入node和dist的queue
                if (cur.node.left != null) {
                    queue.add(new TreeNodeWithPosition(cur.node.left, level, dist - 1));
                    //更新最小值
                    min = Math.min(min, dist - 1);
                }
                // 右子树同时加入node和dist的queue
                if (cur.node.right != null) {
                    queue.add(new TreeNodeWithPosition(cur.node.right, level, dist + 1));
                    max = Math.max(max, dist + 1);
                }
            }

        }
        //输出所有结果
        for (int i = min; i <= max; i++) {
            List<TreeNodeWithPosition> list = map.get(i);
            list.sort((o1, o2) -> {
                if (o1.level != o2.level)
                    return o1.level - o2.level;
                else return o1.node.val - o2.node.val;
            });
            List<Integer> verList = new ArrayList<>();
            for (TreeNodeWithPosition treeNodeWithPosition : list)
                verList.add(treeNodeWithPosition.node.val);

            result.add(verList);
        }
        return result;
    }
}