class Solution {
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = G[1] = 1;
        for (int i = 2; i <= n; i++) {//长度遍历
            int sum = 0;//初始化累加参数
            for (int j = 1; j <= i; j++) {//根节点从1到i，逐次累加
                sum += G[j - 1] * G[i - j];//当前节点对应的二叉树的个数等于左子树的个数与右子树个数的乘积
            }
            G[i] = sum;
        }
        return G[n];
    }
}