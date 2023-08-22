package com.leetcode.solution;

public class _2096_Step_By_Step_Directions_From_a_Binary_Tree_Node_to_Another {
    private final StringBuilder getStartPath = new StringBuilder();
    private final StringBuilder getDestPath = new StringBuilder();

    //  从根结点找到start路径
    private void dfsStart(TreeNode root, int value, StringBuilder path) {
        if (root == null) {
            return;
        }
        // 剪枝：如果已找到 返回
        if (!this.getStartPath.isEmpty()) {
            return;
        }
        if (root.val == value) {
            this.getStartPath.append(path.toString());
            return;
        }
        dfsStart(root.left, value, path.append('L'));
        path.deleteCharAt(path.length() - 1);
        dfsStart(root.right, value, path.append('R'));
        path.deleteCharAt(path.length() - 1);
    }

    // 从根节点找到end路径
    private void dfsDest(TreeNode root, int value, StringBuilder path) {
        if (root == null) {
            return;
        }
        // 剪枝：如果已找到 返回
        if (!this.getDestPath.isEmpty()) {
            return;
        }
        if (root.val == value) {
            this.getDestPath.append(path.toString());
            return;
        }
        dfsDest(root.left, value, path.append('L'));
        path.deleteCharAt(path.length() - 1);
        dfsDest(root.right, value, path.append('R'));
        path.deleteCharAt(path.length() - 1);
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        dfsStart(root, startValue, new StringBuilder());
        dfsDest(root, destValue, new StringBuilder());
        // 如果起始位置为根节点
        if (getStartPath.equals("")) {
            return getDestPath.toString();
        }
        // 如果同一位置
        if (getStartPath.equals(getDestPath)) {
            return "";
        }

        int index = 0;
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        while (index < getStartPath.length() && index < getDestPath.length()) {
            if (getStartPath.charAt(index) != getDestPath.charAt(index)) {
                break;
            } else {
                index++;
            }
        }
        for (int i = index; i < getStartPath.length(); i++) {
            start.append('U');
        }
        end.append(getDestPath.substring(index, getDestPath.length()));
        return start.toString() + end.toString();
    }
}
