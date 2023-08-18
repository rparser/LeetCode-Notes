package com.leetcode.solution;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调递增栈
 * 对于每个数字，如果该数字小于栈顶元素，我们就不断地弹出栈顶元素，直到
 * 1，栈为空
 * 2，或者新的栈顶元素不大于当前数字
 * 3，或者我们已经删除了 k 位数字
 * 对于纯的单调递增，就是丢弃最后的k位
 */
public class _402_Remove_K_Digits {
    public String removeKdigits(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }
        Deque<Character> stack = new LinkedList<>();
        int count = 0; //删除元素的个数

        for (int i = 0; i < num.length(); i++) {
            //如果栈不为空 并且 栈顶元素>当前元素(要保留小的) 并且 删除元素的个数未达到k，栈顶元素出栈
            while (!stack.isEmpty() && stack.peek() > num.charAt(i) && count < k) {
                stack.pop();
                count++;
            } // 0不用放入栈-除非已经有了
            if (!stack.isEmpty() || num.charAt(i) != '0') {
                stack.push(num.charAt(i));
            }
        }

        //如果遍历完了整个字符串，已删除的元素个数仍未达到k
        while (!stack.isEmpty() && count < k) {
            stack.pop();
            count++;
        }
        // 需要保留的数字在栈内, 这时是栈内可能为空比如10，但需要返回0
        if (stack.isEmpty()) {
            return "0";
        }
        // 需要reverse因为是stack，先弹出来的是后放进去的
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
