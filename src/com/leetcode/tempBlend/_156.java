class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) return root;
        TreeNode ans = helper(root.left, root);
        //防止形成环
        root.left = null;
        root.right = null;
        return ans;
    }

    public TreeNode helper(TreeNode left, TreeNode p) {
        TreeNode ans;
        if (left.left == null) {
            ans = left;
        } else {
            ans = helper(left.left, left);
        }
        left.left = p.right;
        left.right = p;
        return ans;
    }
}

class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode ans = null;
        TreeNode cur = root;
        //找最左子树
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        ans = stack.pop();
        cur = ans;
        //反转
        while (!stack.isEmpty()) {
            cur.right = stack.pop();
            cur.left = cur.right.right;
            cur = cur.right;
        }
        //防止形成环
        cur.left = null;
        cur.right = null;
        return ans;
    }
}

作者：wayne_yn
        链接：https://leetcode-cn.com/problems/binary-tree-upside-down/solution/java-di-gui-die-dai-liang-chong-fang-fa-by-wayne_y/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
