class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> distQueue = new LinkedList<>();
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();//<dist, path>
        nodeQueue.add(root);
        distQueue.add(0);
        int min = 0, max = 0; //记录最小最大位置
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            int dist = distQueue.poll();
            if (!map.containsKey(dist)) map.put(dist, new PriorityQueue<>());
            map.get(dist).add(cur.val);
            if (cur.left != null) { //左子树加入队列
                nodeQueue.add(cur.left);
                distQueue.add(dist - 1); //位置-1
                if (min > dist - 1) min = dist - 1;
            }
            if (cur.right != null) { //右子树加入队列
                nodeQueue.add(cur.right);
                distQueue.add(dist + 1); //位置+1
                if (max < dist + 1) max = dist + 1;
            }
        }
        for (int i = min; i <= max; i++) {
            result.add(new ArrayList<>(map.get(i)));
        }
        return result;
    }
}