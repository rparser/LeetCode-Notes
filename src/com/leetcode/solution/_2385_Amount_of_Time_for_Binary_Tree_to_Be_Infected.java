package com.leetcode.solution;

/**
 * 1,对于起始结点，若该结点是一棵树的根结点，那么该结点感染整棵树所需时间为树的高度。如图中的3->10,6。
 * 2,若起始节点存在父结点，则该结点还可以沿着父结点去感染父结点其其他子树。如图中的3->1->5->4->9,2。
 * 假设该起始结点在某结点的左子树上，那么它感染整棵树的时间为从起始节点到树的根结点的距离 + 根结点另一颗子树的高度。
 * 即根结点与起始节点的高度差 + 根结点另一颗子树的高度。
 * 3,以上两种情况是同时进行的，所以最短时间为两者的最大值。
 */
public class _2385_Amount_of_Time_for_Binary_Tree_to_Be_Infected {
    int ans = 0;    // 最短用时
    int depth = -1; // 起始节点的高度

    public int amountOfTime(TreeNode root, int start) {
        dfs(root, 0, start);
        return ans;
    }

    private int dfs(TreeNode root, int level, int start) {
        if (root == null) {
            return 0;
        }
        if (root.val == start) {
            depth = level;
        }                   // 当前节点即起始节点
        int l = dfs(root.left, level + 1, start);                   // 左子树的树高
        boolean inLeft = depth != -1;                               // 起始节点是否在左子树上
        int r = dfs(root.right, level + 1, start);                  // 右子树的树高
        if (root.val == start) {
            ans = Math.max(ans, Math.max(l, r));
        } // 情况1：感染以 start 为根结点的树所需时间
        if (inLeft) {
            ans = Math.max(ans, depth - level + r);
        }        // 情况2：感染以 root 为根结点的树所需时间
        else {
            ans = Math.max(ans, depth - level + l);
        }
        return Math.max(l, r) + 1;                                  // 返回树高
    }
}
