package com.leetcode.doordash;

import com.leetcode.solution.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * DFS树最大长度
 * 思路： At every node, the max path formed by node's val + left child path (>0) + right child path(>0)
 * Since we can only have 1 parent node in path, when we do recursive call,
 * - in dfsPath: for each child node, we add the node's val with either its left or right path or nothing.
 * - keep track the max vs path sum pass through current node as the parent node
 * <p>
 * Complexity：  Time O(n)
 * Space O(h)
 * 模板dfs,int(TreeNode)函数，左右max(0,递归(root.left),更新max=max(max,left+right+root)，返回root+max(left,right)
 * <p>
 * 当前节点的最大路径： max(自己，自己+左边，自己+右边，自己 + 左边 + 右边）
 * 当前节点作为子节点时的贡献：max(自己，自己+左边，自己+右边）
 * 后者相对前者，少了左右都存在的情况。因为作为子节点时，一条路如果同时包含左右，根就被包含了2次，不符合题目只出现一次的限制了。
 */


public class _124_树的最大路径2 {
    int maxPath = Integer.MIN_VALUE;
    int maxPath2 = Integer.MIN_VALUE;
    public int maxPathSumLeafToLeaf(TreeNode root) {
        dfsLeafToLeaf(root);
        return maxPath;
    }
    public int maxPathSum(TreeNode root) {
        dfsPath(root);
        return maxPath;
    }

    //Path pass parent node only once
    //at every node, either add its left or right path or nth for n
    private int dfsPath(TreeNode root) {
        if (root == null)
            return 0;

        int leftSum = Math.max(0, dfsPath(root.left)); // 0代表如果负值就不继续
        int rightSum = Math.max(0, dfsPath(root.right));

        // 更新的是总的最大路经
        maxPath = Math.max(maxPath, leftSum + rightSum + root.val); // update max_sum if it's better to start a new path

        // return的是指当前节点作为子节点（给parent）的最大贡献值
        return root.val + Math.max(leftSum, rightSum); //返回当前分支的长度 - root + 左子树或右子树，取较大值作为当前分支
    }

    // ==========================Leaf to Leaf, 可以为负值
    private int dfsLeafToLeaf(TreeNode root) {
        if (root == null)
            return 0;
        // 由于必须是从叶节点到叶节点 ，所以是否是叶节点，应该有不同的处理方式
        // 当前节点为叶节点时，返回节点值 - 不一样
        if (root.left == null && root.right == null)
            return root.val;

        // 递归计算左右子树的路径和 -基本一样 除去不和0比较，因为负数也要接受
        int leftSum = dfsLeafToLeaf(root.left);
        int rightSum = dfsLeafToLeaf(root.right);

        // 更新最大路径和 - 不一样 只有同时有左右子树才能有leftSum和rightSum才能更新maxPath
        if (root.left != null && root.right != null) {
            maxPath2 = Math.max(maxPath2, leftSum + rightSum + root.val);
        }

        // 返回当前节点值和左右子树中较大的路径和 - 一样
        return root.val + Math.max(leftSum, rightSum);
    }

    // ==========================添加alive属性
    static class TreeNode2 {
        int val;
        boolean alive;
        TreeNode2 left;
        TreeNode2 right;
        TreeNode2(int x) {
            val = x;
        }
    }

    private int dfsAlive(TreeNode2 root) {
        if (root == null)
            return 0;

        // 当前节点为叶节点时，只有 alive 节点才能作为路径起点或终点
        if (root.left == null && root.right == null) {
            if (!root.alive)
                return 0;
            return root.val;
        }

        // 递归计算左右子树的路径和
        int leftSum = dfsAlive(root.left);
        int rightSum = dfsAlive(root.right);

        // 只有 alive 节点才能作为路径的起点或终点
        if (!root.alive) {
            return Math.max(leftSum, rightSum);
        }

        // 更新最大路径和
        maxPath2 = Math.max(maxPath2, leftSum + rightSum + root.val);

        // 返回当前节点值和左右子树中较大的路径和
        return root.val + Math.max(leftSum, rightSum);
    }
//==================打印出路径=================
    //现在，我们使用一个List currentPath 来在DFS过程中保存当前路径。在找到最大路径和时，将 currentPath 赋值给 maxPath，
// 这样就可以在最后打印出路径上的每个节点。注意，我们在计算路径和时，只计算alive节点的value值。
int maxSum = Integer.MIN_VALUE;
    List<TreeNode3> maxPath3 = new ArrayList<>();

    public List<TreeNode3> maxPathSum3(TreeNode3 root) {
        List<TreeNode3> currentPath = new ArrayList<>();
        dfs(root, currentPath);
        return maxPath3;
    }

    private void dfs(TreeNode3 node, List<TreeNode3> currentPath) {
        if (node == null) {
            return;
        }

        currentPath.add(node); // 添加当前节点到路径中

        List<TreeNode3> leftPath = new ArrayList<>(currentPath); // 创建左子树路径的拷贝
        List<TreeNode3> rightPath = new ArrayList<>(currentPath); // 创建右子树路径的拷贝

        dfs(node.left, leftPath); // 递归遍历左子树
        dfs(node.right, rightPath); // 递归遍历右子树

        int pathSum = 0;
        if (node.alive) {
            for (TreeNode3 n : currentPath) {
                pathSum += n.value; // 计算当前路径上alive节点的value值之和
            }
        }

        // 找到最大路径和时更新maxSum和maxPath
        if (pathSum > maxSum) {
            maxSum = pathSum;
            maxPath3 = new ArrayList<>(currentPath);
        }
    }
    class TreeNode3 {
        int value;
        boolean alive;
        TreeNode3 left;
        TreeNode3 right;

        public TreeNode3(int value, boolean alive) {
            this.value = value;
            this.alive = alive;
        }
    }

}
