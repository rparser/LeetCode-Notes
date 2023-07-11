package com.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 112高级版，求树到叶子和为sum的路径
 * 需要存下path, back track path
 * DFS，path存当前路径，先把root加入path,验证sum-root==0则path加入result,递归（左/右，sum-root），path移除最后一个值（刚加的）
 * O(N*logN) Time - O(N*logN) Space 还是O(N)?
 */

public class _113_PathSumII {
    public void dfs(TreeNode root, List<List<Integer>> result, List<Integer> path, int sum) {
        if (root == null) return;
        path.add(root.val); //先加入下一个值
        if (root.left == null && root.right == null && sum - root.val == 0)
            result.add(new ArrayList<>(path)); //验证后path加入result
        dfs(root.left, result, path, sum - root.val);
        dfs(root.right, result, path, sum - root.val);
        path.remove(path.size() - 1); //否则移除最新值
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        dfs(root, result, new ArrayList<>(), sum);
        return result;
    }
}
