class Solution {
    public int maxDepthDfs(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        // if(root.left == null && root.right == null) return Math.max(left, right) +1;
        return Math.max(left, right) + 1;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int count = q.size();
            while (count-- > 0) {
                TreeNode node = q.poll();
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            depth++;
        }
        return depth;
    }
}