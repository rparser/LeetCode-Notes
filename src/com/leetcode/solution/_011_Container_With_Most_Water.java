package com.leetcode.solution;

/**
 * 存水，简单版042
 * <p>
 * Time complexity : O(n). Single pass.
 * Space complexity : O(1). Constant space is used.
 */

public class _011_Container_With_Most_Water {
    //O(N), O(1) 左侧矮，移动左侧是-> 移动右侧面积只会变小不会变大
    // 反之同理
    public int maxArea(int[] height) {
        int result = 0, left = 0, right = height.length - 1;
        while (left < right) {
            result = Math.max(result, Math.min(height[left], height[right]) * (right - left));
            // 左侧矮，移动左侧是因为： 移动右侧面积只会变小不会变大
            // 如果想比当前面积大， 只能矮的动
            if (height[left] < height[right]) {
                left++;
            } else {
                right--; // 相等时可以同时移动两个指针
            }
        }
        return result;
    }
}
