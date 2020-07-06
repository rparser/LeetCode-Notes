public class Solution {
    // O(N) O(1) 注意是子序列不是子数列！！！序列可以间隔
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int min = Integer.MAX_VALUE; //最小值
        int secMin = Integer.MAX_VALUE;//第二元素最小的递增对的第二元素
        for (int num : nums) {
            // 逻辑：每次一定会更新min,sec中的一个，要么返回T
            // 依次更新min,sec，则一旦出现大于min,且大于secMin则一定返回T
            if (num <= min)
                min = num;
            else if (num <= secMin)
                secMin = num;
            else
                return true;
        }
        return false;
    }
}