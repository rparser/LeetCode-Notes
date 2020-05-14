class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        TreeNode cur;
        q.addLast(root);
        while ((cur = q.removeFirst()) != null) {
            q.addLast(cur.left);
            q.addLast(cur.right);
            //会在最后加入null
        }
        while (!q.isEmpty()) {
            // return false;
            if (q.removeLast() != null) {
                return false;
            }
        }
        return true;
    }
}