package com.leetcode.solution;

/**
 * Time complexity : O(N) since it's one pass along the array.
 * Space complexity : O(1), since it's a constant space solution.
 */

public class _053_Maximum_Subarray {
    //    O(N), O(1)
    public int maxSubArray(int[] nums) {
        if (nums.length == 0)
            return 0;
        int curMax = nums[0];
        int allMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // curMax当出现巨大负值时可能会drop，需要保存allMax比如[1,-10],curMax是-9
            curMax = Math.max(nums[i], curMax + nums[i]);
            allMax = Math.max(allMax, curMax);
        }
        return allMax;
    }
    //分治法
    //仔细观察它不仅可以解决区间 [0, n - 1]，还可以用于解决任意的子区间 [l, r] 的问题。
    // 如果我们把 [0,n−1] 分治下去出现的所有子区间的信息都用堆式存储的方式记忆化下来，即建成一颗真正的树之后，
    // 我们就可以在 O(logn) 的时间内求到任意区间内的答案，我们甚至可以修改序列中的值，
    //故渐进时间复杂度为 O(n)。
    //空间复杂度：递归会使用 O(logn) 的栈空间，故渐进空间复杂度为 O(logn)。

    public int maxSubArrayFenzhi(int[] nums) {
        return maxSubArrayPart(nums, 0, nums.length - 1);
    }

    private int maxSubArrayPart(int[] nums, int left, int right) {
        if (left == right)
            return nums[left];

        int mid = (left + right) / 2;
        return Math.max(
                maxSubArrayPart(nums, left, mid),
                Math.max(
                        maxSubArrayPart(nums, mid + 1, right),
                        maxSubArrayAll(nums, left, mid, right)
                )
        );
    }

    //左右两边合起来求解
    private int maxSubArrayAll(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum)
                leftSum = sum;
        }

        sum = 0;

        int rightSum = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum)
                rightSum = sum;
        }
        return leftSum + rightSum;
    }
}
