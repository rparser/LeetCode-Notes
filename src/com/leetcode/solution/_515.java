class Solution {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        //创建一个队列，用于存放当前节点相邻的节点
        Queue<TreeNode> queue = new LinkedList<>();
        //初始化，将根节点放入队列当中
        queue.add(root);
        LinkedList<Integer> result = new LinkedList();
        while (!queue.isEmpty()) {
            //计算当前节点相邻的节点数量
            int size = queue.size();
            int min = Integer.MIN_VALUE;
            //遍历当前节点相邻节点
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                min = Math.max(min, temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            result.add(min);
        }
        return result;
    }
}