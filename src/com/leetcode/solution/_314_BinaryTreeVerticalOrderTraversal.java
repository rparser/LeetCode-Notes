package com.leetcode.solution;

import java.util.*;

/**
 * BFS,纵向打印
 * 思路：用BFS来travese树，原因是要从上往下打印。 建2个Queue，第一个queue存node，第二个存对应的距离。往左就是dist-1，往右就是dist+1。用HashMap在相应的距离存下node。<dist,path>
 * 最后将HashMap由key小到大加入list，即从左打到右-TreeMap(or HashMap记录key的min和max)
 * Complexity: O(N) time O(N) space
 * <p>
 * 关键字：BFS, two queue
 */

public class _314_BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> dist = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();//<dist, path>
        nodes.add(root);
        dist.add(0);
        int min = 0, max = 0;
        while (!nodes.isEmpty()) {
            TreeNode cur = nodes.poll();
            int curdis = dist.poll();
            if (!map.containsKey(curdis)) map.put(curdis, new ArrayList<>());
            map.get(curdis).add(cur.val);
            if (cur.left != null) {
                nodes.add(cur.left);
                dist.add(curdis - 1);
                if (min > curdis - 1) {
                    min = curdis - 1;
                }
            }
            if (cur.right != null) {
                nodes.add(cur.right);
                dist.add(curdis + 1);
                if (max < curdis + 1) {
                    max = curdis + 1;
                }
            }
        }
        for (int i = min; i <= max; i++) {
            result.add(new ArrayList<>(map.get(i)));
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
