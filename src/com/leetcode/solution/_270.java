class Solution {
    public int closestValue(TreeNode root, double target) {
        TreeNode cur = root;
        int curVal;
        int minVal = root.val;

        while (null != cur) {
            curVal = cur.val;
            if (Math.abs(curVal - target) - Math.abs(minVal - target) <0) {
                minVal = curVal;
            }
            // 目标值比当前根值大，搜右子树，反之左子树
            cur = target - curVal > 0 ? cur.right : cur.left;
        }

        return minVal;
    }
}