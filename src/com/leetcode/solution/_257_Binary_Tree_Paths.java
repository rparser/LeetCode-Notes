class Solution {
    private List<String> result = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();

    public List<String> binaryTreePaths(TreeNode root) {
        //自己试一下
        dfs(result, sb, root);
        return result;
    }

    public void dfs(List<String> list, StringBuilder sb, TreeNode node) {
        if (node == null) return;
        int len = sb.length(); //保存当前长度
        sb.append(node.val);
        if (node.left == null && node.right == null)
            list.add(sb.toString());
        else {
            sb.append("->");
            dfs(list, sb, node.left);
            dfs(list, sb, node.right);
        }
        sb.setLength(len); //恢复长度
        // When using StringBuilder, We can just keep track of the
        // length of the StringBuilder before we append anything to it before recursion
        // and afterwards set the length back. Another trick is when to append the "->",
        // since we don't need the last arrow at the end of the string,
        // we only append it before recurse to the next level of the tree.
    }
}