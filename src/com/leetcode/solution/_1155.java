class Solution {
    /**
     * 本题是一个动态规划
     * 如果：3个骰子、每个骰子5个点、目标是10点
     * 由于一个骰子只有1-5，五个数，所以三个骰子为10的结果数量，为最后一个骰子是1点、2点、3点、4点、5点的和
     * 既：两个骰子和为9、8、7、6、5的和
     *
     * @param d      骰子数量
     * @param f      骰子点数
     * @param target 目标和
     * @return 方法数
     */

    public int numRollsToTarget(int d, int f, int target) {
        if (target < d || target > d * f) {
            /*这一步也很好理解，就是所有骰子的和的最小数为每个骰子的值都为1,既1*d，最大值为每个骰子的值都为f,既d*f*/
            return 0;
        }

        int[][] result = new int[31][1001];
        /* row为骰子数量、col为目标点数、int[row][col]为方法数量*/
        int mod = 1000000007;
        result[0][0] = 1;
        /*当前骰子数量*/
        for (int row = 1; row <= d; row++) {
            /*目标值*/
            for (int num = row; num <= target && num <= f * row; num++) {
                /*求当前骰子点数为curNum的时候，获得指定结果值的方法数量*/
                for (int i = 1; i <= f && num - i >= 0; i++) {
                    result[row][num] = (result[row][num] + result[row - 1][num - i]) % mod;
                }
            }
        }
        return result[d][target];
    }
}