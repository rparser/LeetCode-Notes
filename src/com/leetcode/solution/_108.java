class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    class MyTreeNode {
        TreeNode root;
        int start;
        int end;

        MyTreeNode(TreeNode r, int s, int e) {
            this.root = r;
            this.start = s;
            this.end = e;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        Stack<MyTreeNode> rootStack = new Stack<>();
        int start = 0;
        int end = nums.length;
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode curRoot = root;
        rootStack.push(new MyTreeNode(root, start, end));
        while (end - start > 1 || !rootStack.isEmpty()) {
            //考虑左子树
            while (end - start > 1) {
                mid = (start + end) >>> 1; //当前根节点
                end = mid;//左子树的结尾
                mid = (start + end) >>> 1;//左子树的中点
                curRoot.left = new TreeNode(nums[mid]);
                curRoot = curRoot.left;
                rootStack.push(new MyTreeNode(curRoot, start, end));
            }
            //出栈考虑右子树
            MyTreeNode myNode = rootStack.pop();
            //当前作为根节点的 start end 以及 mid
            start = myNode.start;
            end = myNode.end;
            mid = (start + end) >>> 1;
            start = mid + 1; //右子树的 start
            curRoot = myNode.root; //当前根节点
            if (start < end) { //判断当前范围内是否有数
                mid = (start + end) >>> 1; //右子树的 mid
                curRoot.right = new TreeNode(nums[mid]);
                curRoot = curRoot.right;
                rootStack.push(new MyTreeNode(curRoot, start, end));
            }

        }

        return root;
    }

}