class Solution {
    //中序遍历，找到P节点后遍历的下一个节点就是顺序后继节点
    TreeNode res;
    boolean findP = false;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        helper(root, p);
        //判断p是否是最后一个节点
        if (findP) res = null;
        return res;
    }

    private void helper(TreeNode root, TreeNode p) {
        if (root == null) return;
        helper(root.left, p);
        //如果已经找到p，则遍历的下一个节点为res
        if (findP) {
            res = root;
            findP = false;
            return;
        }
        //判断当前节点是否是p
        if (root == p) {
            findP = true;
        }
        helper(root.right, p);
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        TreeNode result = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                result = root;
                root = root.left;
            }
        }

        return result;
    }


}