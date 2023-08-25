package com.leetcode.solution;

import java.util.ArrayDeque;

/**
 * 时间复杂度：O(n)，其中 nnn 为 arr\textit{arr}arr 的长度。虽然我们写了个二重循环，但站在 arr[i]\textit{arr}[i]arr[i] 的视角看，iii 在二重循环中最多入栈出栈各一次，因此整个二重循环的时间复杂度为 O(n)O(n)O(n)。
 * 空间复杂度：O(n)。最坏情况下，栈里面有 O(n)O(n)O(n) 个元素。
 */

public class _907_Sum_of_Subarray_Minimums {
    private static final long MOD = (long) 1e9 + 7;
    // 为简化代码逻辑，可以在遍历前，往 arr 末尾和栈顶分别加一个 −1，当作哨兵。
    public int sumSubarrayMins(int[] arr) {
        var result = 0L;
        var stack = new ArrayDeque<Integer>();
        stack.push(-1); // 哨兵

        for (var r = 0; r <= arr.length; ++r) {
            var x = r < arr.length ? arr[r] : -1; // 假设 arr 末尾有个 -1
            while (stack.size() > 1 && arr[stack.peek()] >= x) {
                var i = stack.pop();
                result += (long) arr[i] * (i - stack.peek()) * (r - i); // 累加贡献
            }
            stack.push(r);
        }
        return (int) (result % MOD);
    }
}
