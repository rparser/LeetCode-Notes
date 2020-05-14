//思考 套用 图的层次遍历 图的第k层数据便是结果
//        二叉树 只有左右，所以需要第三条边，每个节点的父亲节点的父亲节点的链
//        以target节点为图的起始顶点，套用图的层次遍历即可
//        空间复杂度 O(N) 顶点个数

class Solution {
    HashMap<TreeNode, TreeNode> parent;

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        // 初始化父链
        parent = new HashMap<>();
        dfs(root, null);
        Set<TreeNode> set = new HashSet<>();
        queue.add(target);
        set.add(target);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                if (level == K) {
                    for (TreeNode n : queue) {
                        result.add(n.val);
                    }
                    return result;
                }
                TreeNode node = queue.poll();
                // 左子树
                if (node.left != null && !set.contains(node.left)) {
                    queue.add(node.left);
                    set.add(node.left);
                }
                // 右子树
                if (node.right != null && !set.contains(node.right)) {
                    queue.add(node.right);
                    set.add(node.right);
                }
                // 父树
                if (parent.get(node) != null && !set.contains(parent.get(node))) {
                    TreeNode p = parent.get(node);

                    queue.add(p);
                    set.add(p);
                }
            }
            level++;
        }
        return result;
    }
}
