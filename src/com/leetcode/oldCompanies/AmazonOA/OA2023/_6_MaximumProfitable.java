package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调栈。单调递减。
 * 对每个元素J找左边第一个比他大的位置L和右边第一个比他大的位置R。然后J是L-1 .. R-1的最大值，这个时候计算L-1 .. J 的子数组数量 + J ..R-1子数组数量。
 * 具体实现的话：当遍历到R的时候，会弹栈出J，此时栈顶是L，然后累加r - j + j - l- 1。
 */
public class _6_MaximumProfitable {
    public static int countMaximumProfitable(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new LinkedList<>();
        int cnt = 0;
        for (int r = 0; r <= n; r++) {
            int cur = (r == n) ? Integer.MAX_VALUE : arr[r];
            while (!stack.isEmpty() && arr[stack.peek()] < cur) {
                int j = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek();
                cnt += r - j + j - l - 1; // 多了一个点会多出的子数组数量
            }
            stack.push(r);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] stockPrice = {5, 3, 2, 4, 1, 3, 4, 2, 5};
        System.out.println(countMaximumProfitable(stockPrice)); // Output: 7
        int[] stockPrice2 = {2, 3, 2};
        System.out.println(countMaximumProfitable(stockPrice2)); // Output: 5
        int[] stockPrice3 = {3, 1, 3, 5};
        System.out.println(countMaximumProfitable(stockPrice3)); // Output: 10
        int[] stockPrice4 = {1, 5, 2};
        System.out.println(countMaximumProfitable(stockPrice4)); // Output: 5
        int[] stockPrice5 = {3, 1, 3, 2};
        System.out.println(countMaximumProfitable(stockPrice5)); // Output: 10
    }
}
