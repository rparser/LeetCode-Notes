package com.leetcode.solution;

import java.util.*;

public class _1047_remove_all_adjacent_duplicates_in_string {
    public String removeDuplicates(String S) {
        /* 只需删除重复项即可，因此可以使用栈实现
         * 每次添加时比较是否与栈顶元素相同
         *   - 若相同则删除栈顶元素且不插入
         *   - 若不相同则插入新元素
         * 时间复杂度：O(N)
         * 空间复杂度：O(N)
         */
        Deque<Character> deque = new LinkedList<>();

        for (char value : S.toCharArray()) {
            // peek is peekFirst
            if (deque.isEmpty() || value != deque.peek())
                // addFirst
                deque.push(value);
            else
                //removeFirst
                deque.poll();
        }

        StringBuilder result = new StringBuilder();

        while (!deque.isEmpty())
            result.append(deque.removeLast());

        return result.toString();
    }
}
