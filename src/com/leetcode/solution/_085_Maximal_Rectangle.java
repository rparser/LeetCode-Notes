package com.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

public class _085_Maximal_Rectangle {
    // 类似84 维持单调栈monotonic stack
    //融合DP和stack O(mn) O(2n) -n为列数cLen如果知道m,n大小可以变为min(m,n)for space
    public int maximalRectangle(char[][] matrix) {
        int rLen = matrix.length, cLen = matrix[0].length, max = 0;
        // curHeight记录从顶到此位置（竖直）高度
        int[] curHeight = new int[cLen + 1]; // +1是为了下面for(i <= cLen)，可以最后再计算一次

        for (char[] chars : matrix) {
            // 每行计算, stack只有当前行有效
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(-1); //设置底部(为了计算第一个值)

            for (int i = 0; i <= cLen; i++) { //最后一个cLen处理最右侧一个
                // 如果为1则高度增加1，否则清零
                if (i < cLen && chars[i] == '1')
                    curHeight[i] += 1;
                else
                    curHeight[i] = 0;
                // 单调递增栈，保证出现下降时即可计算当前index的高度的面积
                while (stack.peek() != -1 && curHeight[i] < curHeight[stack.peek()])
                    // 上一个高度pop出来 ×（当前index - 上上index -1） (-1是因为这两个都不包含)
                    max = Math.max(max, curHeight[stack.pop()] * (i - stack.peek() - 1));

                stack.push(i);
            }
        }
        return max;
    }
}
