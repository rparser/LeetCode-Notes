package com.leetcode.solution;

import java.util.*;

/**
 * BFS,纵向打印
 * 思路：用BFS来travese树，原因是要从上往下打印。 建2个Queue，第一个queue存node，第二个存对应的距离。往左就是dist-1，往右就是dist+1。用HashMap在相应的距离存下node。<dist,path>
 * 最后将HashMap由key小到大加入list，即从左打到右-TreeMap(or HashMap记录key的min和max)
 * Complexity: O(N) time O(N) space
 * <p>
 * 关键字：BFS, two queue
 * 2队列(node和距离),map距离:list<path>，int min/max距离。模板while，取点，如果map没有则建新list,添加左右子树进队列
 */

public class _314_BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> distQueue = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();//<dist, path>
        nodeQueue.add(root);
        distQueue.add(0);
        int min = 0, max = 0; //记录最小最大位置
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            int dist = distQueue.poll();
            if (!map.containsKey(dist)) map.put(dist, new ArrayList<>());
            map.get(dist).add(cur.val);
            if (cur.left != null) { //左子树加入队列
                nodeQueue.add(cur.left);
                distQueue.add(dist - 1); //位置-1
                if (min > dist - 1) min = dist - 1;
            }
            if (cur.right != null) { //右子树加入队列
                nodeQueue.add(cur.right);
                distQueue.add(dist + 1); //位置+1
                if (max < dist + 1) max = dist + 1;
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
