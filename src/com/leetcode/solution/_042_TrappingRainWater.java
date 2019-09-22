package com.leetcode.solution;

/**
 * 水槽存水问题
 * <p>
 * Time complexity: O(n). Single iteration of O(n).
 * Space complexity: O(1) extra space. Only constant space required for left, right, left_max and right_max.
 */

public class _042_TrappingRainWater {
    public int trap(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {//如果左侧小于右侧，此处离左侧最高值差x，则此处至少可以保存x（因为右侧有比左侧最高更高的！）
                leftMax = Math.max(leftMax, height[left]); //左侧最高值
                res += leftMax - height[left];//取最高值和当前值的差
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
