package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

/**
 * [2,1,5,6,2,3]输出
 * 0    1
 * 2
 * 3    4
 * 6
 * 2    4 - 这时达到最大值
 * 10
 * 逻辑是：到某个地方下降时，开始把前面上升的一个一个弹出
 * 于是高度是:那个之前柱的高度
 * 宽度是：从前一个(i-1)到peek的距离
 */

class _084_Largest_Rectangle_in_Histogram {
    //    维护一个单调递增的栈monotonic stack，就可以找到 left_i 和 right_i。left, right为当前index高度下矩形的左右边界
    public int largestRectangleAreaStack(int[] heights) {
        Deque<Integer> stack = new LinkedList<>(); // 储存index
        stack.push(-1); // 用来放最左边计算宽度
        int maxArea = 0;
//        [2,1,5,6,2,3] -> 最后剩[1,2,3],2,5,6在for loop里弹出
        for (int i = 0; i < heights.length; i++) {
            // 单调递增栈，保证出现下降时即可计算当前index的高度的面积
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                System.out.println(stack.peek()+ "    " + i);
                // 上一个高度pop出来 ×（当前index - 上上index -1） (-1是因为这两个都不包含)
                maxArea = Math.max(heights[stack.pop()] * (i - 1 - stack.peek()), maxArea);
                System.out.println(maxArea);
            }
            stack.push(i);
        }
        // 处理stack剩余的，这些是能通到最右的
        while (stack.peek() != -1) {
            maxArea = Math.max(heights[stack.pop()] * (heights.length - stack.peek() - 1), maxArea);
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        int[] left_i = new int[n];
        int[] right_i = new int[n];
        left_i[0] = -1;
        right_i[n - 1] = n;
        int res = 0;
        for (int i = 1; i < n; i++) {
            int tmp = i - 1;
            while (tmp >= 0 && heights[tmp] >= heights[i]) tmp = left_i[tmp];
            left_i[i] = tmp;
        }
        for (int i = n - 2; i >= 0; i--) {
            int tmp = i + 1;
            while (tmp < n && heights[tmp] >= heights[i]) tmp = right_i[tmp];
            right_i[i] = tmp;
        }
        for (int i = 0; i < n; i++) res = Math.max(res, (right_i[i] - left_i[i] - 1) * heights[i]);
        return res;
    }
}
