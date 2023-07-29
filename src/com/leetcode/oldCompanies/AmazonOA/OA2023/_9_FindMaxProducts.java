package com.leetcode.oldCompanies.AmazonOA.OA2023;

import java.util.Stack;

/**
 * 在这里，我们可以使用单调栈的思想来解决这个问题。我们从左到右遍历数组，并使用一个栈来维护一个递增的索引序列。当我们遇到一个元素小于栈顶元素时，
 * 说明我们不能再继续向右扩展子数组，因为要满足条件“选择的产品数量在满足条件的情况下尽可能大”，我们需要计算当前的子数组的产品数量，并更新最大可选产品数。
 * 然后将当前索引入栈继续遍历。最终，我们返回最大可选产品数。
 */
public class _9_FindMaxProducts {
    public static int findMaxProducts(int[] numProducts) {
        int n = numProducts.length;
        int maxProducts = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 使用单调栈来维护递增的索引序列
            // 如果遇到一个元素小于栈顶元素，说明不能再继续向右扩展子数组了
            while (!stack.isEmpty() && numProducts[i] < numProducts[stack.peek()]) {
                // 弹出栈顶元素，计算当前的子数组的产品数量
                int height = numProducts[stack.pop()];
                // 计算子数组的宽度，即栈中当前元素的索引和栈顶元素的索引之差
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                // 更新最大可选产品数，取当前子数组的产品数量和之前的最大值
                maxProducts = Math.max(maxProducts, height * width);
                System.out.println(i + "    " + maxProducts);
            }
            // 将当前索引入栈继续遍历
            stack.push(i);
        }

        // 处理栈中剩余的索引，这些索引对应的子数组可以一直扩展到数组末尾
        while (!stack.isEmpty()) {
            int height = numProducts[stack.pop()];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxProducts = Math.max(maxProducts, height * width);
        }

        return maxProducts;
    }

    public static void main(String[] args) {
        int[] numProducts = {7, 4, 5, 2, 6, 5};
        System.out.println(findMaxProducts(numProducts)); // 输出: 12
    }
}
