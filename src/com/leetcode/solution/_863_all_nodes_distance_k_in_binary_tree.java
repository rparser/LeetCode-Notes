package com.leetcode.solution;//思考 套用 图的层次遍历 图的第k层数据便是结果
//        二叉树 只有左右，所以需要第三条边，每个节点的父亲节点的父亲节点的链
//        以target节点为图的起始顶点，套用图的层次遍历即可
//        空间复杂度 O(N) 顶点个数

import java.util.*;

class _863_all_nodes_distance_k_in_binary_tree {
    //Huffman Coding 两个编码去掉相同前缀时剩下的位数和
    // 首先将整棵树编码，当其向左时记为1，向右时记为3，根节点为1
    //111 到 131 的距离为 3（111的位数） + 4（1313的位数） - 2（两次重复） * 1（只有最高位1相同） = 5

    //保存编码<值，编码> O(N), O(N)
    Map<Integer, Long> map = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> list = new ArrayList<>();
        //下面的编码树
        codeTree(root, 1);
        //找到目标节点的target code
        long targetCode = 0;
        for (Integer key : map.keySet()) {
            if (key == target.val) {
                targetCode = map.get(key);
                break;
            }
        }
        //遍历其他节点看是否符合距离要求
        for (Integer key : map.keySet())
            if (distanceCode(map.get(key), targetCode) == K)
                list.add(key);

        return list;
    }

    //编码
    public void codeTree(TreeNode root, long value) {
        if (root == null) return;
        map.put(root.val, value);
        //也可以加其他值比如加3
        codeTree(root.left, value * 10);
        codeTree(root.right, value * 10 + 1);
    }

    public int distanceCodeString(long val, long target) {
        int sameL = 0;
        long large = Math.max(val, target);
        long small = Math.min(val, target);

        String strL = String.valueOf(large);
        String strS = String.valueOf(small);

        for (int i = 0; i < strS.length(); i++)
            if (strS.charAt(i) == strL.charAt(i))
                sameL++;
            else
                break;
        // 实际公式 strL.length() + strS.length() - 2 * (sameL - 1) - 1(least ancestor) -1(自身);
        return strL.length() + strS.length() - 2 * sameL;
    }

    public int distanceCode(long val, long target) {
        long sameL = 0;
        long large = Math.max(val, target);
        long small = Math.min(val, target);

        long L1 = (long) Math.log10(large) + 1;
        long L2 = (long) Math.log10(small) + 1;
        //补齐后找差值(前面差了几位)
        double diff = Math.abs(small * (Math.pow(10, L1 - L2)) - large);
        // diff为0说明正好补齐，否则就是前缀差值
        sameL = (diff == 0) ? L1 : L1 - (long) (Math.log10(diff + 1)) - 1;

        return (int) (L1 + L2 - 2 * sameL);
    }


    //以目标结点为中心，进行扩散遍历
    public List<Integer> distanceKCenter(TreeNode root, TreeNode target, int K) {
        List<Integer> retList = new ArrayList<>();

        Map<TreeNode, TreeNode> parentsTreeMap = new HashMap<>();
        setParentTreeNode(root, parentsTreeMap, null);

        Queue<TreeNode> checkQueue = new LinkedList<>();
        checkQueue.add(target);

        Set<TreeNode> hasCheck = new HashSet<>();
        hasCheck.add(target);

        while (!checkQueue.isEmpty() && K > 0) {
            K--;
            int size = checkQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = checkQueue.poll();
                if (treeNode.left != null && hasCheck.add(treeNode.left))
                    checkQueue.add(treeNode.left);
                if (treeNode.right != null && hasCheck.add(treeNode.right))
                    checkQueue.add(treeNode.right);
                if (parentsTreeMap.containsKey(treeNode) && hasCheck.add(parentsTreeMap.get(treeNode)))
                    checkQueue.add(parentsTreeMap.get(treeNode));
            }
        }
        while (!checkQueue.isEmpty())
            retList.add(checkQueue.poll().val);

        return retList;
    }


    //整理数据，给每个结点指定父结点，然后保存在Map中，供之后使用
    private void setParentTreeNode(TreeNode root, Map<TreeNode, TreeNode> parentTreeNodeMap, TreeNode parent) {
        if (root == null) return;

        if (parent != null)
            parentTreeNodeMap.put(root, parent);

        setParentTreeNode(root.left, parentTreeNodeMap, root); //记录父节点，变成无向图
        setParentTreeNode(root.right, parentTreeNodeMap, root);
    }
}
