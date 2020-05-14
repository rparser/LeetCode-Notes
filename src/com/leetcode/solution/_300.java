// Dynamic programming.
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //dp[i]代表到数组第i个位置的最长上升子序列
        int[] dp = new int[n + 1];
        int res = 1;
        //枚举第i个位置
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            //枚举它所有前面位置
            for (int j = 1; j < i; j++) {
                //如果第i个位置的数比前面数字大，符合最长上升子序列，更新
                if (nums[j - 1] < nums[i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                //记录全局最大值
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    //使用二分优化
//优化：一旦前面有两个dp值一样了，比如dp[i] = dp[j],并缺nums[i] > nums[j] ，那就只要考虑第j个就可以了
//启示：同样的dp值，存一个坐标，这个坐标对应的nums[index]值最小。
//怎么做？对于每个dp值，保存对应的nums[i]的值
//序列是单调上升的，在单调上升中找最后一个比自己小的数用二分法 lon2n，二分法很重要
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] a = new int[n];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int idx = Arrays.binarySearch(a, 0, res, nums[i]);
            if (idx < 0) {
                idx = -idx - 1;
            }
            //把这个nums[i]放在插入位置上
            a[idx] = nums[i];
            if (idx == res) {
                res++;
            }
        }
        return res;
    }
}