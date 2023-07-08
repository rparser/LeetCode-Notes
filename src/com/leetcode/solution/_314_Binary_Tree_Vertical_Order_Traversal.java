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

public class _314_Binary_Tree_Vertical_Order_Traversal {
    // O(N), O(N)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> distQueue = new LinkedList<>();
        //<距离，这个距离的 val>
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        nodeQueue.add(root);
        distQueue.add(0);
        //记录最左最右
        int min = 0, max = 0;
        while (!nodeQueue.isEmpty()) {
            //两个queue分别取出node和dist
            TreeNode cur = nodeQueue.poll();
            int dist = distQueue.poll();
            if (!map.containsKey(dist))
                map.put(dist, new ArrayList<>());

            map.get(dist).add(cur.val);
            // 左子树同时加入node和dist的queue
            if (cur.left != null) {
                nodeQueue.add(cur.left);
                distQueue.add(dist - 1); //位置-1
                //更新最小值
                min = Math.min(min, dist - 1);
            }
            // 右子树同时加入node和dist的queue
            if (cur.right != null) {
                nodeQueue.add(cur.right);
                distQueue.add(dist + 1); //位置+1
                max = Math.max(max, dist + 1);
            }
        }

        for (int i = min; i <= max; i++)
            result.add(new ArrayList<>(map.get(i)));

        return result;
    }
}
