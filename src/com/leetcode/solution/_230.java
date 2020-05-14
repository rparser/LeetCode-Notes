class Solution{
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int num = 0;
        int res = -1;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 节点不为空一直压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; // 考虑左子树
            }
            // 节点为空，就出栈
            cur = stack.pop();
            // 当前值加入
            num++;
            if (num == k) {
                res = cur.val;
                break;
            }
            // 考虑右子树
            cur = cur.right;
        }
        return res;
    }
//递归

    int num = 0;
    int res;

    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root, k);
        return res;
    }

    private void inorderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, k);
        num++;
        if (num == k) {
            res = node.val;
            return;
        }
        inorderTraversal(node.right, k);
    }
}