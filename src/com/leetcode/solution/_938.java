//后序遍历（深度优先）+减枝算法

class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        int res = 0;
        //减枝条件
        if(root.val > L){
            res+=rangeSumBST(root.left,L,R);
        }
        //减枝条件
        if(root.val<R){
            res+=rangeSumBST(root.right,L,R);
        }
        if(root.val>=L && root.val<=R){
            res+=root.val;
        }
        return res;
    }
}