package com.leetcode.solution;

import java.util.*;

/**
 * 当node为leftBound左边界时，node.left也是左边界
 * 当node为leftBound左边界时，node.left为空，则node.right也可以leftBound左边界。
 * Bottom的所有都要加入其中。
 * rightBound也是如此。
 * <p>
 * 我们可以循环调用dfs，初始化leftBound和rightBound两个boolean参数，一层层判断。先加入左边，加入bottom，然后得到两个子树加入，最后加入右边界。
 */

public class _545_BoundaryofBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.add(root.val);
        getBounds(root.left, res, true, false);
        getBounds(root.right, res, false, true);
        return res;
    }

    private void getBounds(TreeNode node, List<Integer> res, boolean leftBound, boolean rightBound) {
        if (node == null) return;
        if (leftBound) {
            res.add(node.val); //如果是左子树加值
        }

        getBounds(node.left, res, leftBound, rightBound && node.right == null); //继续查左子树，且右标和右子树都为空
        getBounds(node.right, res, leftBound && node.left == null, rightBound); //继续查右子树，且左标和左子树都为空

        if (!leftBound && !rightBound && node.left == null && node.right == null) { //如果是底加值
            res.add(node.val);
        }

        if (rightBound) { //如果是右子树加值
            res.add(node.val);
        }
    }
}
