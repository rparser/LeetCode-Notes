package com.leetcode.solution;

import org.junit.*;
import org.junit.runner.*;

import java.util.Arrays;

public class _209_Minimum_Size_Subarray_Sum {
    // O(N), O(1)
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int result = Integer.MAX_VALUE;

        int left = 0, right = 0, curr_sum = 0;

        while (right < len) {
            //先找到 >=s 的，如果还没找到，则right向后移一位，更新curr_sum
            while (curr_sum < s && right < len) {
                curr_sum += nums[right];
                right++;
            }
            // 找到后,left向右，一个一个排除看是否还大于
            while (curr_sum >= s && left < len) {
                // 更新result
                result = Math.min(right - left, result);
                curr_sum -= nums[left];//start要向后移来缩小res大小
                left++;
            }
            // 当排除太多导致不够时会重新进入while loop,右指针向右
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
  //  O(nlogn), O(n)
    public int minSubArrayLenBinarySearch(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++)
            sums[i] = sums[i - 1] + nums[i - 1];

        for (int i = 1; i <= n; i++) {
            // 找到最小的 sum[j] - sum[i]
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            // binarySearch可以返回负值以表示该在的位置
            if (bound < 0)
                bound = -bound - 1;
            // 如果在范围内，找最小值
            if (bound <= n)
                ans = Math.min(ans, bound - (i - 1));
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        JUnitCore.main("com.leetcode.solution._209MinimumSizeSubarraySum");
    }

    @Test
    public void testSolution() {
        int[] input = {1, 2, 3};
        Assert.assertEquals(2, minSubArrayLen(2, input));
    }
}
