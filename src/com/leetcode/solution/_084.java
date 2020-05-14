//要想找到第 i 位置最大面积是什么？是以i 为中心，向左找第一个小于 heights[i] 的位置 left_i；
//        向右找第一个小于于 heights[i] 的位置 right_i，即最大面积为 heights[i] * (right_i - left_i -1)，
//
//当我们找 i 左边第一个小于 heights[i] 如果 heights[i-1] >= heights[i]
//        其实就是和 heights[i-1] 左边第一个小于 heights[i-1] 一样。依次类推，右边同理。


class Solution {
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

    //    利用单调栈
//    维护一个单调递增的栈，就可以找到 left_i 和 right_i。
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            // 栈顶元素不是第一个元素 -1 且数组呈下降关系时，什么时候结束呢？
            // 显然是当栈顶元素为 -1 或者 heights[i] ≥ heights[stack.peek()] 跳出循环直接压栈
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                // 将栈中的序号弹出，计算最大面积
                maxArea = Math.max(heights[stack.pop()] * (i - stack.peek() - 1), maxArea);
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxArea = Math.max(heights[stack.pop()] * (heights.length - stack.peek() - 1), maxArea);
        }
        return maxArea;
    }
}
