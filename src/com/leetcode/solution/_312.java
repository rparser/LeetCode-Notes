class Solution { //分治法 8ms 72.11 %
    public int maxCoins(int[] nums) {
        cache = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                cache[i][j] = -1;
            }
        }
        int result = getMaxCoins(nums, 0, nums.length - 1);
        return result;
    }

    int[][] cache;

    int getMaxCoins(int[] nums, int start, int end) {
        //结束条件
        if (start <= end) {
            if (cache[start][end] != -1)
                return cache[start][end];
        }
        if (start == end) {
            int result = (start - 1 < 0 ? 1 : nums[start - 1]) * nums[start] * (end + 1 > nums.length - 1 ? 1 : nums[end + 1]);
            return result;
        }
        int i;
        //状态转移方程
        int maxCoins = 0;
        int temp = 0;
        for (i = start; i <= end; i++) {
            temp = (start - 1 < 0 ? 1 : nums[start - 1]) * nums[i] * (end + 1 > nums.length - 1 ? 1 : nums[end + 1]) + getMaxCoins(nums, start, i - 1) + getMaxCoins(nums, i + 1, end);
            maxCoins = Math.max(maxCoins, temp);
        }
        if (end >= start) {
            cache[start][end] = maxCoins;
        }
        return maxCoins;
    }

    //动态规划
    public int maxCoins(int[] nums) {
        int dp[][] = new int[nums.length][nums.length];
        if (nums.length == 0) {  //沙雕测试用例[]
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                fill(dp, nums, j, j + i);
            }
        }
        return dp[0][nums.length - 1];
    }

    void fill(int[][] dp, int nums[], int start, int end) {
        int max = 0;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, (start - 1 < 0 ? 1 : nums[start - 1]) * nums[i] * (end + 1 > nums.length - 1 ? 1 : nums[end + 1]) + (start > i - 1 ? 0 : dp[start][i - 1]) + (end < i + 1 ? 0 : dp[i + 1][end]));
        }
        dp[start][end] = max;
    }
}