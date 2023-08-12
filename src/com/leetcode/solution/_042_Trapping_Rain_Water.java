package com.leetcode.solution;

/**
 * 水槽存水问题
 * <p>
 * Time complexity: O(n). Single iteration of O(n).
 * Space complexity: O(1) extra space. Only constant space required for left, right, left_max and right_max.
 * 大概思路：
 * 当前坐标left的储水量是(Min(left左侧，left右侧的最大高度)-left高度)
 * 假设两柱子分别为 i，j。那么就有 iLeftMax,iRightMax,jLeftMx,jRightMax 这个变量。
 * 由于 j>i ，故 jLeftMax>=iLeftMax，iRigthMax>=jRightMax.
 */

public class _042_Trapping_Rain_Water {
    public int trap(int[] height) {
        int result = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0; // 此个坐标以左/右的最高值
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // 对left来说leftMax是准的,对right来说rightMax是准的
            if (leftMax < rightMax) { // leftMax<rightMax则要取更小的，那就是做left
                result += leftMax - height[left];//取最高值和当前值的差
                left++;
            } else {
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }
}
