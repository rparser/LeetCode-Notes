package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

class _1130_Minimum_Cost_Tree_From_Leaf_Values {
    public int mctFromLeafValues(int[] arr) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(Integer.MAX_VALUE);
        int ans = 0;
        for (int n : arr) {
            while (stack.element() < n)
                ans += stack.pop() * Math.min(stack.element(), n);

            stack.push(n);
        }
        while (stack.size() > 2)
            ans += stack.pop() * stack.element();

        return ans;
    }
}
