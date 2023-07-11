package com.leetcode.solution;

/**
 * 孤单整数
 * <p>
 * Time complexity: O(logn)
 * Space complexity: O(1)
 * while(l < r),m为中值，看m与对应n=m^1是否一致，判断问题在左侧l=m+1还是右侧r=m
 */

public class _540_Single_Element_in_a_Sorted_Array {
    //O(log(n/2)), O(1)
    // 对所有偶数索引进行搜索，直到遇到第一个其后元素不相同的索引。
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // mid保持偶数
            if (mid % 2 == 1)
                mid--;
            // 如果偶索引和后一个一样，说明单次数字出现在后半段
            if (nums[mid] == nums[mid + 1])
                lo = mid + 2;
                //否则前半段
            else
                hi = mid;
        }
        return nums[lo];
    }
}
